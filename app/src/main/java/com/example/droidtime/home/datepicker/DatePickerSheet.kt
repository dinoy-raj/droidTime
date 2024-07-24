package com.example.droidtime.home.datepicker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen(state: DatePickerState) {
    Column() {
        DatePicker(
            state = state, colors = DatePickerDefaults.colors().copy(
                containerColor = Color.White,
                todayContentColor = Color.Black,
                todayDateBorderColor = Color.Black,
                selectedDayContentColor = Color.White,
                selectedDayContainerColor = Color.Black,
                dayContentColor = Color.Black.copy(alpha = .6f),
                selectedYearContentColor = Color.Black,
                headlineContentColor = Color.Black,
                subheadContentColor = Color.Black,
                titleContentColor = Color.Black,
                weekdayContentColor = Color.Black,
                dividerColor = Color.Black,
                yearContentColor = Color.Black,
                currentYearContentColor = Color.Black,
                selectedYearContainerColor = Color.Gray,
                navigationContentColor = Color.Black.copy(alpha = .6f),

            )
        )
    }
}