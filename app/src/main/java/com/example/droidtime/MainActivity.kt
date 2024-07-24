package com.example.droidtime

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.droidtime.home.RelativeLocalised
import com.example.droidtime.home.viewmodels.HomeViewModel
import com.example.droidtime.ui.theme.DroidTimeTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = HomeViewModel()
        setContent {
            DroidTimeTheme {
                RelativeLocalised(viewModel = viewModel)
            }
        }
    }
}
