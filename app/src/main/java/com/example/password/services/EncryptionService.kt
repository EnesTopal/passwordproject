package com.example.password.services


import com.example.password.databaseElements.EncryptedDataDao
import com.example.password.utils.encrypt
import com.example.password.entities.EncryptedDataModel
import com.example.password.entities.EncryptedDataStore
import com.example.password.utils.generateSecretKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.crypto.SecretKey


fun Encyptioning(orgPassword: String,dao: EncryptedDataDao) {
    val encryptedDataToDb: EncryptedDataStore = EncryptedDataStore(0, "", "")
    val secretKey = generateSecretKey()
    val encryptedPassword = encrypt(orgPassword,secretKey)
    encryptedDataToDb.secretKey = secretKey
    encryptedDataToDb.encryptedPassword = encryptedPassword
    CoroutineScope(Dispatchers.IO).launch {
        dao.insertPassword(encryptedDataToDb)
    }
}

fun Encyptioning(orgPassword: String, secretKey: String, dao: EncryptedDataDao){
    val encryptedDataToDb: EncryptedDataStore = EncryptedDataStore(0, "", "")
    val encryptedPassword = encrypt(orgPassword,secretKey)
    encryptedDataToDb.secretKey = "Application's own secret key used"
    encryptedDataToDb.encryptedPassword = encryptedPassword
    CoroutineScope(Dispatchers.IO).launch {
        dao.insertPassword(encryptedDataToDb)
    }
}