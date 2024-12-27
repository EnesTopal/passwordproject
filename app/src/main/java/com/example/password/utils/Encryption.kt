package com.example.password.utils

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


fun generateSecretKey(): String {
    val keyGen = KeyGenerator.getInstance("AES")
    keyGen.init(128) // 128-bit key
//    return keyGen.generateKey()
    return encodeKey(keyGen.generateKey())
}

fun encodeKey(secretKey: SecretKey): String {
    return Base64.getEncoder().encodeToString(secretKey.encoded)
}

fun decodeKey(encodedKey: String): SecretKey {
    val decodedBytes = Base64.getDecoder().decode(encodedKey)
//    val decodedBytes = Base64.decode(encodedKey, Base64.DEFAULT)
    return SecretKeySpec(decodedBytes, 0, decodedBytes.size, "AES")
}

fun encrypt(data: String, stringSecretKey: String): String {
    val lvSecretKey = decodeKey(stringSecretKey)
    val cipher = Cipher.getInstance("AES")
    cipher.init(Cipher.ENCRYPT_MODE, lvSecretKey)
    val encryptedBytes = cipher.doFinal(data.toByteArray())
    return Base64.getEncoder().encodeToString(encryptedBytes)
}

fun decrypt(encryptedData: String, secretKey: SecretKey): String {
    val cipher = Cipher.getInstance("AES")
    cipher.init(Cipher.DECRYPT_MODE, secretKey)
    val decodedBytes = Base64.getDecoder().decode(encryptedData)
    return String(cipher.doFinal(decodedBytes))
}