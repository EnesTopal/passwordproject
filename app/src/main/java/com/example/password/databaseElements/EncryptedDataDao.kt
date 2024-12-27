package com.example.password.databaseElements

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.password.entities.EncryptedDataStore

@Dao
interface EncryptedDataDao {
@Insert
suspend fun insertPassword(encryptedData: EncryptedDataStore)

@Query("SELECT * FROM encrypted_datas")
suspend fun getAllPasswords(): List<EncryptedDataStore>

@Query("DELETE FROM encrypted_datas")
suspend fun clearDatabase()
}

//film ismi-tarih
//kitap ismi-yazar-tarih