package com.example.droidtime.home.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidtime.home.models.Pattern
import com.example.droidtime.home.models.PatternSelection
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.d("dino1", "inside view model cleared")
    }

    /** Pattern **/
    var patternList: SnapshotStateList<Pattern> = SnapshotStateList()
    var patternSelected = mutableStateOf<PatternSelection>(PatternSelection.Default)


    fun replacePatternList(list: List<Pattern>) = viewModelScope.launch {
        Log.d("dino1", "updated pattern from viewModel ${list.size}")
        patternList = list.toMutableStateList()
    }

    fun updatePatternList(index: Int, state: Boolean) = viewModelScope.launch {
        patternList[index] = Pattern(patternList[index].name, state)
    }

    fun updatePatternSelected(pattern: PatternSelection) = viewModelScope.launch {

        if(patternSelected.value is PatternSelection.Custom && pattern is PatternSelection.Default)
        {
            patternList.replaceAll {
                Pattern(it.name, true)
            }
        }
        patternSelected.value = pattern
    }

}