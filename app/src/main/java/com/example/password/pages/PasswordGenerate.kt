package com.example.password.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.password.BuildConfig
import com.example.password.databaseElements.DatabaseProvider
import com.example.password.services.Decryptioning
import com.example.password.services.Encyptioning
import com.example.password.R
import com.example.password.databaseElements.EncryptedDataDao
import com.example.password.utils.RandomPasswordGenerator
import com.example.password.entities.EncryptedDataModel
import com.example.password.entities.EncryptedDataStore
import com.example.password.ui.theme.PasswordTheme
import com.example.password.utils.Boxes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.password.utils.CustomSwitch.customSwitch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGenerate(navController: NavController, dao: EncryptedDataDao) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp //411--412
    val screenWeight = configuration.screenWidthDp  //659--732
    Log.e("screen","$screenWeight")
    Log.e("screen","$screenHeight")
    val boxes = Boxes;
    val allowedSymbols = listOf('@', '#', '$', '%', '!') + ('0'..'9')
    val password = remember { mutableStateOf<String>("") }
    val encryptedData =
        remember { mutableStateOf<EncryptedDataModel>(EncryptedDataModel("", "testt")) }
    val encryptedDataToDb: EncryptedDataStore = EncryptedDataStore(0, "", "")
    val hasUpperCase = remember(password.value) { password.value.any { it.isUpperCase() } }
    val hasMinLength = remember(password.value) { password.value.length >= 8 }
    val hasSymbol = remember(password.value) { password.value.any { it in allowedSymbols } }
    val switchState = remember { mutableStateOf(false) }
    var isTruePassword = remember(
        hasSymbol,
        hasMinLength,
        hasUpperCase
    ) { hasUpperCase && hasMinLength && hasSymbol }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Password Generate") },
        )
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("ShowPasswords") },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.password_screen),
                        contentDescription = ""
                    )
                }
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy((screenHeight/41.187).dp)
            ) {
                Box {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding((screenWeight/18).dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy((screenHeight/60).dp)
                    ) {
                        if (!hasUpperCase) {
                            boxes.redBox("Büyük harfli karaktere sahip değil")
                        } else {
                            boxes.greenBox("Büyük harf içeriyor")
                        }
                        if (!hasMinLength) {
                            boxes.redBox("Minimum uzunluk 8 karakter olmalıdır")
                        } else {
                            boxes.greenBox("Yeterli uzunlukta")
                        }
                        if (!hasSymbol) {
                            boxes.redBox("İstenilen sembollerden birine sahip değil")
                        } else {
                            boxes.greenBox("İstenilen sembollerden birine sahip")
                        }
                    }
                }

                TextField(value = password.value, onValueChange = { password.value = it })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "App's Secret Key", modifier = Modifier.padding((screenWeight/41.1).dp,(screenHeight/65.9).dp))
                    customSwitch(switchDurum = switchState)
                    Text(text = "New Secret Key", modifier = Modifier.padding((screenWeight/41.1).dp,(screenHeight/65.9).dp))
                }
                Row(
                    modifier = Modifier.padding((screenWeight/26).dp, 0.dp),
                    horizontalArrangement = Arrangement.spacedBy((screenWeight/27.5).dp),
                ) {
                    Button(modifier = Modifier.weight(1f),
                        onClick = { password.value = RandomPasswordGenerator() }) {
                        Text(
                            text = "Generate Random\n Password",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                lineHeight = 20.sp,
                            )
                        )
                    }
                    Button(modifier = Modifier.weight(1f),
                        onClick = {
                            if (switchState.value) {
                                Encyptioning(password.value, dao)
                            } else{
                                Encyptioning(password.value, BuildConfig.MY_SECRET_KEY,dao)
                            }
                                password.value = ""
                        }) {
                        Text(
                            text = "Save Password\nAs Encyrpted",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                lineHeight = 20.sp,
                            )
                        )
                    }
                }
            }

//            Text(
//                modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp),
//                text = "Your encyrpted password is:\n ${
//                    encryptedData.value.component2().toString()
//                }",
//                textAlign = TextAlign.Center
//            )
//            Box() {
//                var decrypted = remember { mutableStateOf("") }
//                Button(onClick = {
//                    decrypted.value = Decryptioning(encryptedData.value)
//                }) {
//                    Text(
//                        text = "Decrpyth is: ${decrypted.value}",
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordGeneratePreview() {
    PasswordTheme {
        val navController = rememberNavController()
        PasswordGenerate(navController, dao = DatabaseProvider.dao)
    }
}