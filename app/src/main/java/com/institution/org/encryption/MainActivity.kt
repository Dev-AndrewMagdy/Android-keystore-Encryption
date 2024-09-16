package com.institution.org.encryption

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoManager: CryptoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cryptoManager = CryptoManager()

        val tx = findViewById<TextView>(R.id.textView)

        findViewById<Button>(R.id.decrypt).setOnClickListener {
            tx.text = decrypt()
        }

        findViewById<Button>(R.id.encrypt).setOnClickListener {
            tx.text = encrypt()
        }

    }

    private fun encrypt(): String {
        val bytes = "andrew".encodeToByteArray()
        val file = File(this.filesDir, "secret.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fos = FileOutputStream(file)

        return cryptoManager.encrypt(bytes = bytes, outputStream = fos).toString()
    }

    private fun decrypt(): String {
        val file = File(this.filesDir, "secret.txt")
        return cryptoManager.decrypt(inputStream = FileInputStream(file)).decodeToString()
    }
}