package com.example.droidtime.home.sections

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.droidtime.home.models.BottomSheets
import kotlinx.datetime.LocalDate

@Composable
fun HomeTimeSelectionCard(
    now: LocalDate,
    hour1: Int,
    minute1: Int,
    seconds1: Int,
    section: Int,
    dateSelectionType: Boolean,
    onDateSelectionChange: () -> Unit,
    openSheets: (BottomSheets) -> Unit
) {


    val animateColor1 by
    animateColorAsState(
        targetValue = if (dateSelectionType) Color.Black else Color.White,
        label = ""
    )

    val animateColor2 by
    animateColorAsState(
        targetValue = if (dateSelectionType) Color.White else Color.Black,
        label = ""
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
    )
    {

        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                )
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .height(30.dp)
                        .width(120.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray.copy(alpha = .4f),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                )
                {
                    Row(modifier = Modifier.fillMaxSize()) {

                        Box(
                            modifier = Modifier
                                .bounceClick(.8f, true)
                                {
                                    onDateSelectionChange()
                                }
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    color = animateColor2,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text(
                                text = "Now",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    color = animateColor1,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                        }

                        Box(
                            modifier = Modifier
                                .bounceClick(.8f, true)
                                {
                                    onDateSelectionChange()
                                }
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    color = animateColor1,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text(
                                text = "Custom",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    color = animateColor2,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                        }
                    }


                }

            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.height(70.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 20.dp)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .bounceClick(.8f, dateSelectionType)
                        {
                            openSheets(BottomSheets.DatePicker(section))
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    )
                    {

                        AnimatedContent(
                            targetState = now.dayOfMonth,
                            transitionSpec = {
                                if (targetState > initialState) {
                                    slideInVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { -it } togetherWith slideOutVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { it }
                                } else {
                                    slideInVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { it } togetherWith slideOutVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { -it }
                                }
                            }, label = ""
                        ) { count ->
                            Text(
                                text = "$count",
                                style = TextStyle(
                                    fontSize = 50.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(.05f)
                            .fillMaxSize()
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )

                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    )
                    {
                        AnimatedContent(
                            targetState = now.monthNumber,
                            transitionSpec = {
                                if (targetState > initialState) {
                                    slideInVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { -it } togetherWith slideOutVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { it }
                                } else {
                                    slideInVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { it } togetherWith slideOutVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { -it }
                                }
                            }, label = ""
                        ) { count ->
                            Text(
                                text = "$count",
                                style = TextStyle(
                                    fontSize = 50.sp,
                                    color = Color.Black
                                )
                            )
                        }

                    }

                    Box(
                        modifier = Modifier
                            .weight(.05f)
                            .fillMaxSize()
                    )
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )

                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(2.5f)
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    )
                    {

                        AnimatedContent(
                            targetState = now.year,
                            transitionSpec = {
                                if (targetState > initialState) {
                                    slideInVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { -it } togetherWith slideOutVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { it }
                                } else {
                                    slideInVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { it } togetherWith slideOutVertically(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessVeryLow
                                        )
                                    ) { -it }
                                }
                            }, label = ""
                        ) { count ->
                            Text(
                                text = "$count",
                                style = TextStyle(
                                    fontSize = 50.sp,
                                    color = Color.LightGray
                                )
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier.height(30.dp))

            AnimatedVisibility(visible = dateSelectionType) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .height(120.dp)
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .background(
                            Color.Gray.copy(alpha = .04f),
                            RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .bounceClick(.8f, dateSelectionType)
                            {
                                openSheets(BottomSheets.TimePicker(section))
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        )
                        {
                            AnimatedContent(
                                targetState = hour1,
                                transitionSpec = {
                                    if (targetState > initialState) {
                                        slideInVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { -it } togetherWith slideOutVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { it }
                                    } else {
                                        slideInVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { it } togetherWith slideOutVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { -it }
                                    }
                                }, label = ""
                            ) { count ->
                                Text(
                                    text = "$count",
                                    style = TextStyle(
                                        fontSize = 50.sp,
                                        color = Color.LightGray
                                    )
                                )
                            }
                        }



                        Box(
                            modifier = Modifier
                                .weight(.05f)
                                .fillMaxHeight()
                                .background(color = Color.White)
                        )



                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        )
                        {
                            AnimatedContent(
                                targetState = minute1,
                                transitionSpec = {
                                    if (targetState > initialState) {
                                        slideInVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { -it } togetherWith slideOutVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { it }
                                    } else {
                                        slideInVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { it } togetherWith slideOutVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { -it }
                                    }
                                }, label = ""
                            ) { count ->
                                Text(
                                    text = "$count",
                                    style = TextStyle(
                                        fontSize = 50.sp,
                                        color = Color.LightGray
                                    )
                                )
                            }
                        }




                        Box(
                            modifier = Modifier
                                .weight(.05f)
                                .fillMaxHeight()
                                .background(color = Color.White)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        )
                        {
                            AnimatedContent(
                                targetState = seconds1,
                                transitionSpec = {
                                    if (targetState > initialState) {
                                        slideInVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { -it } togetherWith slideOutVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { it }
                                    } else {
                                        slideInVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { it } togetherWith slideOutVertically(
                                            animationSpec = spring(
                                                dampingRatio = Spring.DampingRatioLowBouncy,
                                                stiffness = Spring.StiffnessVeryLow
                                            )
                                        ) { -it }
                                    }
                                }, label = ""
                            ) { count ->
                                Text(
                                    text = "$count",
                                    style = TextStyle(
                                        fontSize = 50.sp,
                                        color = Color.LightGray
                                    )
                                )
                            }
                        }
                    }

                }
            }


        }


    }
}