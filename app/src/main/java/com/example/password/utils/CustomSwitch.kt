package com.example.password.utils

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

object CustomSwitch {
    @Composable
    fun customSwitch(switchDurum: MutableState<Boolean>) {
        val switchDefaults = SwitchDefaults
        Switch(
            checked = switchDurum.value, onCheckedChange = { switchDurum.value = it },
            colors = SwitchDefaults.colors(
                checkedIconColor = SwitchDefaults.colors().uncheckedIconColor,
                checkedTrackColor = SwitchDefaults.colors().uncheckedTrackColor,
                checkedThumbColor = SwitchDefaults.colors().uncheckedThumbColor,
                checkedBorderColor = SwitchDefaults.colors().uncheckedBorderColor,
            )
        )
    }
}