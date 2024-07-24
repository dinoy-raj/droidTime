package com.example.droidtime.home.sections

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeSectionHeader(sectionTitle: String) {
    Text(
        text = sectionTitle,
        style = TextStyle(
            color = Color.Gray.copy(alpha = .3f),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .padding(10.dp)
            .padding(start = 10.dp)
    )
}