package com.example.droidtime.home.sections

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidtime.home.bounceClick

@Composable
fun HomeTabRow(selectedTab: Int, onTabSelection: (Int) -> Unit) {

    // tab color switching

    val tabColor1 by animateColorAsState(
        targetValue = if (selectedTab == 0) Color.Black else Color.Transparent,
        label = ""
    )
    val tabColor2 by animateColorAsState(
        targetValue = if (selectedTab == 1) Color.Black else Color.Transparent,
        label = ""
    )

    val tabColor3 by animateColorAsState(
        targetValue = if (selectedTab == 2) Color.Black else Color.Transparent,
        label = ""
    )


    // layout
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 20.dp)
            .background(
                color = Color.LightGray.copy(alpha = .09f),
                shape = RoundedCornerShape(10.dp)
            )
    )
    {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .bounceClick(bounce = .8f) {
                        onTabSelection(0)
                    }
                    .background(
                        color = tabColor1,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center,
            )
            {
                Text(
                    text = "Relative",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == 0) Color.White else Color.Gray.copy(
                            alpha = .8f
                        )
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .bounceClick(bounce = .8f) {
//                        onTabSelection(1)
                    }
                    .background(
                        color = tabColor2,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center,
            )
            {
                Text(
                    text = "Absolute",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == 1) Color.White else Color.Gray.copy(
                            alpha = .8f
                        )
                    )
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .bounceClick(bounce = .8f) {
//                        onTabSelection(2)
                    }
                    .background(
                        color = tabColor3,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center,
            )
            {
                Box(
                    modifier = Modifier.padding(6.dp).border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    ).align(Alignment.TopEnd)
                )
                {
                    Text(
                        text = "Soon",
                        modifier = Modifier.padding(5.dp),
                        style = TextStyle(fontSize = 7.sp, color = Color.LightGray)
                    )
                }
                Text(
                    text = "UnRelative",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == 2) Color.White else Color.Gray.copy(
                            alpha = .8f
                        )
                    )
                )
            }
        }
    }
}