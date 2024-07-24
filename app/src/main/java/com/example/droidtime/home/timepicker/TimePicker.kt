package com.example.droidtime.home.timepicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerScreen(state: TimePickerState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimePicker(
            state = state,
            colors = TimePickerDefaults.colors().copy(
                clockDialColor = Color.Black,
                selectorColor = Color.White,
                clockDialSelectedContentColor = Color.Black,
                clockDialUnselectedContentColor = Color.LightGray,
                timeSelectorSelectedContainerColor = Color.Black,
                timeSelectorUnselectedContentColor = Color.White,
                timeSelectorSelectedContentColor = Color.White,
                timeSelectorUnselectedContainerColor = Color.LightGray,
                periodSelectorSelectedContentColor = Color.White,
                periodSelectorBorderColor = Color.LightGray,
                periodSelectorUnselectedContainerColor = Color.White,
                periodSelectorSelectedContainerColor = Color.Black,
                periodSelectorUnselectedContentColor = Color.Black
            ),
            layoutType = TimePickerLayoutType.Vertical,
        )
    }
}