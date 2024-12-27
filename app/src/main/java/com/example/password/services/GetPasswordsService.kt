package com.example.password.services

import androidx.compose.runtime.MutableState
import com.example.password.databaseElements.DatabaseProvider
import com.example.password.entities.EncryptedDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun GetPasswords(myData: MutableState<List<EncryptedDataStore>>){
    val dao = DatabaseProvider.dao
    var dataFromDb: List<EncryptedDataStore>
    CoroutineScope(Dispatchers.IO).launch {
        dataFromDb = dao.getAllPasswords()
        withContext(Dispatchers.Main) {
            myData.value = dataFromDb
        }
    }

}

//fun GetPasswords(){
//    val dao = DatabaseProvider.dao
//    var dataFromDb: List<EncryptedDataStore>
//    CoroutineScope(Dispatchers.IO).launch {
//        dataFromDb = dao.getAllPasswords()
//        withContext(Dispatchers.Main) {
//            myData.value = dataFromDb
//        }
//    }
//}