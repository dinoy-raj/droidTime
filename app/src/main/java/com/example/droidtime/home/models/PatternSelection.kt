package com.example.droidtime.home.models

sealed class PatternSelection {
    data object Default : PatternSelection()
    data object Custom : PatternSelection()
}