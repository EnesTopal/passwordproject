package com.example.password.services

import android.util.Log
import com.example.password.BuildConfig
import com.example.password.utils.decodeKey
import com.example.password.utils.decrypt
import com.example.password.entities.EncryptedDataModel

fun Decryptioning(encryptedData: EncryptedDataModel): String{
    if (encryptedData.secretKey == "Application's own secret key used"){
        val realSecretKey = decodeKey(BuildConfig.MY_SECRET_KEY)
        return decrypt(encryptedData.encryptedPassword,realSecretKey)
    }
    else{
        val realSecretKey = decodeKey(encryptedData.secretKey)
        return decrypt(encryptedData.encryptedPassword,realSecretKey)
    }
}