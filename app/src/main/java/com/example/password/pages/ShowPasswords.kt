package com.example.password.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.password.databaseElements.DatabaseProvider
import com.example.password.databaseElements.EncryptedDataDao
import com.example.password.entities.EncryptedDataStore
import com.example.password.services.GetPasswords
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowPasswords(navController: NavController, dao: EncryptedDataDao) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp //411--412
    val screenWeight = configuration.screenWidthDp  //659--732
    var myData = remember { mutableStateOf<List<EncryptedDataStore>>(emptyList()) }
    GetPasswords(myData)
    val data = myData.value
    var context = LocalContext.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Passwords") })
    },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp,(screenWeight/35).dp,0.dp,(screenWeight/7).dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.clearDatabase()
                            GetPasswords(myData)
                        }
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                context,
                                "All passwords deleted\nYou will be directed to create screen",
                                Toast.LENGTH_SHORT
                            ).show()
                            delay(2000)
                            navController.navigate("PasswordGenerate")
                        }
                    }
                ) {
                    Text(text = "Clear Database")
                }
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(data) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .border(2.dp, Color.Blue, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Text(
                            modifier = Modifier.padding(7.dp),
                            text = "Key: ${item.secretKey}\nPassword: ${item.encryptedPassword}"
                        )
                    }
                }

            }
        }
    }
}