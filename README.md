# Android key-Store Encryption

![Logo](https://raw.githubusercontent.com/Dev-AndrewMagdy/Android-keystore-Encryption/master/Keystore.png)




This project demonstrates how to use Android Keystore to encrypt and decrypt sensitive data in an Android application using Kotlin. The Android Keystore system allows developers to securely store cryptographic keys and use them for encryption operations, ensuring data is secure and protected from malicious users.

## How Android Keystore Works
The Android Keystore System allows you to:

## Features

- Generate cryptographic keys that never leave the device and cannot be extracted.
- Use these keys for encryption, decryption, or signing data without directly exposing the keys to your application.

## Key Points

- The keys are stored securely in a hardware-backed keystore on supported devices.
- Keys cannot be extracted, which reduces the risk of them being compromised.
- Keystore can be used with AES, RSA, and HMAC cryptographic algorithms.

![Logo](https://raw.githubusercontent.com/Dev-AndrewMagdy/Android-keystore-Encryption/master/image1.png)


## Encryption Process

- Key Generation: The key is generated using KeyGenerator and stored in the Keystore.
- Encryption: Data is encrypted using the generated key and an encryption algorithm (AES/GCM).
- Decryption: Encrypted data is decrypted using the same key stored in the Keystore.


## Usage/Examples

- Key Generation
The Keystore key is generated using the KeyGenerator class:

```val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
keyGenerator.init(
    KeyGenParameterSpec.Builder("myKeyAlias",
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .build()
)
val secretKey = keyGenerator.generateKey()

```
- Encryption
Encryption and Decryption
```
val cipher = Cipher.getInstance("AES/GCM/NoPadding")
cipher.init(Cipher.ENCRYPT_MODE, secretKey)
val encryptionIv = cipher.iv
val encryptedData = cipher.doFinal(dataToEncrypt.toByteArray(Charsets.UTF_8))
```
- Decryption
```
val cipher = Cipher.getInstance("AES/GCM/NoPadding")
cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, encryptionIv))
val decryptedData = cipher.doFinal(encryptedData).toString(Charsets.UTF_8)
```

## Conclusion

The Android Keystore allows the app to store the generated key securely and ensures it is only accessible to the app.

Security Considerations:
- Non-Extractable Keys: Keys stored in the Keystore cannot be extracted, providing enhanced security.
- Hardware-backed Security: If available, the Keystore uses hardware security features to further protect keys.