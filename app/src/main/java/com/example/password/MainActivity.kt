package com.example.password

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalConfiguration
import com.example.password.databaseElements.DatabaseProvider
import com.example.password.pages.AddUtils
import com.example.password.ui.theme.PasswordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordTheme {
                DatabaseProvider.initialize(applicationContext)
//                StartDatabase(applicationContext)
                PageOrientation()
//                AddUtils()
            }
        }
    }
}

