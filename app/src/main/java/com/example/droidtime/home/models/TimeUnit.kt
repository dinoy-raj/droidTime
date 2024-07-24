package com.example.droidtime.home.models

sealed interface TimeUnit{
    data object Now: TimeUnit
    data object Custom: TimeUnit
}