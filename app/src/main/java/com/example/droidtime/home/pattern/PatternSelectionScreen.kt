package com.example.droidtime.home.pattern

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidtime.home.bounceClick
import com.example.droidtime.home.models.PatternSelection
import com.example.droidtime.home.viewmodels.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PatternSelectionScreen(
    viewModel: HomeViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray.copy(alpha = .05f))
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray.copy(alpha = .5f),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(40.dp),
            contentAlignment = Alignment.Center
        )
        {

            FlowRow(
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            )
            {
                for (i in 0..<viewModel.patternList.size) {
                    Box(
                        modifier = Modifier
                            .bounceClick(
                                .8f,
                                viewModel.patternSelected.value is PatternSelection.Custom
                            )
                            {
                                viewModel.updatePatternList(i, !viewModel.patternList[i].selected)
                            }
                            .background(
                                color = if (viewModel.patternList[i].selected) Color.White else Color.LightGray.copy(
                                    alpha = .5f
                                ), shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (viewModel.patternList[i].selected) Color.Black else Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    {

                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = viewModel.patternList[i].name,
                                style = TextStyle(fontSize = 13.sp, color = Color.Gray)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                    }
                }

            }
        }



        Spacer(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}