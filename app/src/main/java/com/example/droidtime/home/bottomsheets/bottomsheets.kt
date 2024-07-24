package com.example.droidtime.home.bottomsheets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.droidtime.home.datepicker.DatePickerScreen
import com.example.droidtime.home.models.BottomSheets
import com.example.droidtime.home.pattern.PatternSelectionScreen
import com.example.droidtime.home.timepicker.TimePickerScreen
import com.example.droidtime.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheets(
    sheets: BottomSheets,
    state: DatePickerState,
    timePickerState: TimePickerState,
    viewModel: HomeViewModel
) {
    Box(
        modifier = Modifier
    )
    {
        when (sheets) {
            is BottomSheets.DatePicker -> {
                DatePickerScreen(state = state)
            }

            is BottomSheets.PatternSelection -> {
                PatternSelectionScreen(viewModel)
            }

            is BottomSheets.TimePicker -> {
                TimePickerScreen(state = timePickerState)
            }
        }
    }

}