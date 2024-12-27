package com.example.password.entities

import java.io.Serializable

data class EncryptedDataModel(
    val secretKey: String,
    val encryptedPassword: String
): Serializable
