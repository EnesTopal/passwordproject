package com.example.password.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encrypted_datas")
data class EncryptedDataStore(
    @PrimaryKey(autoGenerate = true) var uuid: Int = 0,
    @ColumnInfo(name = "secretKey") var secretKey: String?,
    @ColumnInfo(name = "encryptedPassword") var encryptedPassword: String?
)
