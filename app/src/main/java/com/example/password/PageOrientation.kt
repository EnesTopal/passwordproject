package com.example.password

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.password.databaseElements.DatabaseProvider
import com.example.password.pages.PasswordGenerate
import com.example.password.pages.ShowPasswords


@Composable
fun PageOrientation() {
    var navController = rememberNavController()
    val dao = DatabaseProvider.dao
    
    NavHost(navController = navController, startDestination = "PasswordGenerate" ){
        composable("PasswordGenerate") { PasswordGenerate(navController = navController, dao = dao) }
        composable("ShowPasswords") { ShowPasswords(navController = navController, dao = dao) }
    }

}