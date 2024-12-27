package com.example.password.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.password.R

object Boxes {
    @Composable
    fun redBox(message: String){
        Box {
            Row (horizontalArrangement = Arrangement.spacedBy(3.dp))
                {
                Icon(painter = painterResource(id = R.drawable.password_invalid),
                    contentDescription = "invalid",
                    Modifier.background(Color.Red, shape = RoundedCornerShape(8.dp)) )
                Text(text = message)
            }
        }
    }

    @Composable
    fun greenBox(message: String){
        Box {
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                Icon(painter = painterResource(id = R.drawable.password_done),
                    contentDescription = "done",
                    Modifier.background(Color.Green, shape = RoundedCornerShape(8.dp)) )
                Text(text = message)
            }
        }
    }
}