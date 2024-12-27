package com.example.password.databaseElements

import android.content.Context

object DatabaseProvider {
    lateinit var dao: EncryptedDataDao
        private set

    fun initialize(context: Context) {
        val database = AppDatabase.getInstance(context)
        dao = database.encryptedDataDao()
    }
}