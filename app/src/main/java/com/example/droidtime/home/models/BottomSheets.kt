package com.example.droidtime.home.models


sealed class BottomSheets {
    data class DatePicker(val selected: Int) : BottomSheets()
    data class PatternSelection(
        val patternList: MutableList<Pattern>,
        val selectedPatternSelection: com.example.droidtime.home.models.PatternSelection
    ) : BottomSheets()

    data class TimePicker(val selected: Int) : BottomSheets()
}