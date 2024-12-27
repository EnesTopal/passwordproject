package com.example.password.databaseElements

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.password.entities.EncryptedDataStore

@Database(entities = [EncryptedDataStore::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun encryptedDataDao(): EncryptedDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "encrypted_datas"
                ).build()
                INSTANCE = instance
                instance
            }
        }
        
    }
}