package com.example.droidtime.home

import DroidTime
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidtime.home.bottomsheets.HomeBottomSheets
import com.example.droidtime.home.models.BottomSheets
import com.example.droidtime.home.models.Pattern
import com.example.droidtime.home.models.PatternSelection
import com.example.droidtime.home.sections.HomeSectionHeader
import com.example.droidtime.home.sections.HomeTabRow
import com.example.droidtime.home.sections.HomeTimeSelectionCard
import com.example.droidtime.home.viewmodels.HomeViewModel
import com.example.droidtime.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toLocalDateTime
import relative.utility.RelativeLocalisedUnit
import relative.utility.RelativeUnitPattern
import utility.UnitStyle
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("ReturnFromAwaitPointerEventScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelativeLocalised(viewModel: HomeViewModel) {


    /** Tab Row State **/
    var selectedTab by remember { mutableIntStateOf(0) }
    var response by remember {
        mutableStateOf("")
    }
    var selectedPattern by remember {
        mutableStateOf<PatternSelection>(
            PatternSelection.Default
        )
    }

    LaunchedEffect(key1 = selectedTab) {
        selectedPattern = PatternSelection.Default
    }

    /** Date Time Selection 1 **/

    // Date
    var now by remember { mutableStateOf(LocalDate(2000, 6, 12)) }
    var nowMilli by remember { mutableStateOf<Long?>(null) }

    // Time
    var nowHour by remember { mutableIntStateOf(0) }
    var nowMinute by remember { mutableIntStateOf(0) }

    var nowSelectionType by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = nowSelectionType) {
        if (!nowSelectionType) {
            val current = Clock.System.now()
            now = current.toLocalDateTime(TimeZone.currentSystemDefault()).date
            nowMilli = current.toEpochMilliseconds()
            nowHour = 0
            nowMinute = 0
        }
    }


    /** Date Time Selection 2 **/

    // Date
    var instant by remember { mutableStateOf(LocalDate(2000, 6, 12)) }
    var instantMilli by remember { mutableStateOf<Long?>(null) }

    // Time
    var instantHour by remember { mutableIntStateOf(0) }
    var instantMinute by remember { mutableIntStateOf(0) }

    var instantSelectionType by remember { mutableStateOf(true) }


    LaunchedEffect(key1 = instantSelectionType) {
        if (!instantSelectionType) {
            val current = Clock.System.now()
            instant = current.toLocalDateTime(TimeZone.currentSystemDefault()).date
            instantMilli = current.toEpochMilliseconds()
            instantHour = 0
            instantMinute = 0
        }
    }


    /** Date Time Selection General **/
    var dateSelectionType by remember { mutableIntStateOf(0) }

    // initial date manipulating animation
    LaunchedEffect(key1 = Unit) {
        delay(500)
        instantMilli = Clock.System.now().toEpochMilliseconds()
        nowMilli = instantMilli
        now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        instant = now
    }

    /** Picker States **/
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds()
    )
    val timePickerState = rememberTimePickerState()


    val bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var currentBottomSheet by remember { mutableStateOf<BottomSheets?>(null) }


    /** Sheet Controls **/
    LaunchedEffect(key1 = bottomSheetState.currentValue != SheetValue.Expanded) {

        Log.d("time30", "inside closed")
        when (currentBottomSheet) {
            is BottomSheets.DatePicker -> {
                Log.d("time30", " inside date picker")
                if ((currentBottomSheet as BottomSheets.DatePicker).selected == 0) {
                    nowMilli = datePickerState.selectedDateMillis
                    now = datePickerState.selectedDateMillis?.let {
                        Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                            .toKotlinLocalDate()
                    }!!
                    Log.d("time30", " updated local date")
                } else {
                    instantMilli = datePickerState.selectedDateMillis
                    instant = datePickerState.selectedDateMillis?.let {
                        Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                            .toKotlinLocalDate()
                    }!!
                }
            }

            is BottomSheets.PatternSelection -> {}
            is BottomSheets.TimePicker -> {
                if ((currentBottomSheet as BottomSheets.TimePicker).selected == 0) {
                    nowHour = timePickerState.hour
                    nowMinute = timePickerState.minute
                    Log.d("time30", " updated local time")
                } else {
                    instantHour = timePickerState.hour
                    instantMinute = timePickerState.minute
                }
            }

            null -> {}
        }
    }


    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val openSheet: (sheet: BottomSheets) -> Unit = {
        scope.launch {
            bottomSheetState.hide()

            when (it) {
                is BottomSheets.DatePicker -> {
                    if (it.selected == 0) {
                        datePickerState.selectedDateMillis = nowMilli
                    } else {
                        datePickerState.selectedDateMillis = instantMilli
                    }
                }

                is BottomSheets.PatternSelection -> {

                }

                is BottomSheets.TimePicker -> {
                }
            }

            Log.d(
                "dino1", "inside open ${
                    if (it is BottomSheets.PatternSelection) it.patternList.size else ""
                }"
            )

            currentBottomSheet = it
            scaffoldState.bottomSheetState.expand()
        }
    }


    var selectedUnitStyle by remember {
        mutableIntStateOf(0)
    }

    val unitColor1 by animateColorAsState(
        targetValue = if (selectedUnitStyle == 0) Color.Black else Color.LightGray.copy(alpha = .4f),
        label = ""
    )

    val unitColor2 by animateColorAsState(
        targetValue = if (selectedUnitStyle == 1) Color.Black else Color.LightGray.copy(alpha = .4f),
        label = ""
    )

    val unitColor3 by animateColorAsState(
        targetValue = if (selectedUnitStyle == 2) Color.Black else Color.LightGray.copy(alpha = .4f),
        label = ""
    )


    LaunchedEffect(key1 = Unit) {

        when (selectedTab) {
            0 -> {
                val list = mutableListOf<Pattern>()
                RelativeLocalisedUnit.entries.forEach {
                    list.add(Pattern(name = it.name, selected = true))
                }

                viewModel.replacePatternList(list)
            }

            1 -> {
                val list = mutableListOf<Pattern>()
//                AbsoluteUnits.entries.forEach {
//                    list.add(Pattern(name = it.name, selected = true))
//                }
                viewModel.replacePatternList(list)

            }

            2 -> {
                viewModel.replacePatternList(mutableStateListOf())
            }
        }
    }

    LaunchedEffect(key1 = selectedTab) {
        when (selectedTab) {
            0 -> {

                val list = mutableListOf<Pattern>()
                RelativeLocalisedUnit.entries.forEach {
                    list.add(Pattern(name = it.name, selected = true))
                }

                viewModel.replacePatternList(list)
            }

            1 -> {
                val list = mutableListOf<Pattern>()
//                AbsoluteUnits.entries.forEach {
//                    list.add(Pattern(name = it.name, selected = true))
//                }
                viewModel.replacePatternList(list)

            }

            2 -> {
                viewModel.replacePatternList(mutableStateListOf())
            }
        }
    }


    // default animations
    val defaultWidth by
    animateFloatAsState(
        targetValue = if (viewModel.patternSelected.value == PatternSelection.Default) .7f else 1f,
        label = ""
    )

    val defaultBorderColor by
    animateColorAsState(
        targetValue = if (viewModel.patternSelected.value == PatternSelection.Default)
            Color.Transparent else Color.LightGray,
        label = ""
    )


    // custom animations
    val customWidth by
    animateFloatAsState(
        targetValue = if (viewModel.patternSelected.value == PatternSelection.Custom) .7f else 1f,
        label = ""
    )

    val customBorderColor by
    animateColorAsState(
        targetValue = if (viewModel.patternSelected.value == PatternSelection.Custom)
            Color.Transparent else Color.LightGray,
        label = ""
    )




    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetContainerColor = Color.White,
        sheetShape = RoundedCornerShape(15.dp),
        sheetTonalElevation = 30.dp,
        sheetShadowElevation = 100.dp,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            currentBottomSheet?.let {
                HomeBottomSheets(
                    sheets = it,
                    state = datePickerState,
                    timePickerState,
                    viewModel
                )
            }
        },
        containerColor = Color.Gray.copy(alpha = .02f),
        contentColor = Color.Gray.copy(alpha = .02f)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color.Gray.copy(alpha = .02f))
        ) {

            // Top Spacer
            item { Box(modifier = Modifier.height(100.dp)) }

            // Tab Row Selection
            item {
                HomeTabRow(selectedTab = selectedTab) { tab -> selectedTab = tab }
            }

            //  Spacer
            item { Box(modifier = Modifier.height(80.dp)) }

            // Date Time Picker Header 0th
            item { HomeSectionHeader(sectionTitle = "Now/Comparator") }

            // Date Time Picker Card 0th
            item {
                HomeTimeSelectionCard(
                    now = now,
                    hour1 = nowHour,
                    minute1 = nowMinute,
                    seconds1 = 0,
                    section = 0,
                    onDateSelectionChange = {
                        nowSelectionType = !nowSelectionType
                    },
                    dateSelectionType = nowSelectionType
                )
                {
                    openSheet(it)
                }
            }

            item { Spacer(modifier = Modifier.height(50.dp)) }


            // Date Time Picker Header 1
            item { HomeSectionHeader(sectionTitle = "Instant") }


            // Date Time Picker Card 1
            item {
                HomeTimeSelectionCard(
                    now = instant,
                    hour1 = instantHour,
                    minute1 = instantMinute,
                    seconds1 = 0,
                    section = 1,
                    onDateSelectionChange = {
                        instantSelectionType = !instantSelectionType
                    },
                    dateSelectionType = instantSelectionType
                )
                {
                    openSheet(it)
                }
            }

            item {
                Box(modifier = Modifier.height(50.dp))
            }

            item {
                Text(
                    text = "Pattern",
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

            item {
                Box(modifier = Modifier.height(10.dp))
            }

            item {

                Row(modifier = Modifier.padding(horizontal = 20.dp)) {

                    Box(
                        modifier = Modifier
                            .bounceClick(.8f)
                            {
                                viewModel.updatePatternSelected(PatternSelection.Default)
                            }
                            .weight(1f)
                            .height(50.dp)
                            .background(Color.Black, shape = RoundedCornerShape(10.dp))
                    )
                    {

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(1f - defaultWidth)
                                    .background(
                                        Color.Transparent,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Icon(
                                    Icons.Rounded.CheckCircle,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color.White,
                                    contentDescription = ""
                                )
                            }
                        }

                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(defaultWidth)
                                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                                    .border(
                                        width = 1.dp,
                                        defaultBorderColor,
                                        RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Text(
                                    "Default",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                )
                            }
                        }

                    }

                    Spacer(modifier = Modifier.weight(.1f))

                    Box(
                        modifier = Modifier
                            .bounceClick(.8f)
                            {
                                viewModel.updatePatternSelected(PatternSelection.Custom)
                                openSheet(
                                    BottomSheets.PatternSelection(
                                        patternList = viewModel.patternList,
                                        selectedPatternSelection = PatternSelection.Custom
                                    )
                                )
                            }
                            .weight(1f)
                            .height(50.dp)
                            .background(Color.Black, shape = RoundedCornerShape(10.dp))
                    )
                    {

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(1f - customWidth)
                                    .background(
                                        Color.Transparent,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Icon(
                                    Icons.Rounded.CheckCircle,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp),
                                    contentDescription = ""
                                )
                            }
                        }

                        Row(modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(customWidth)
                                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                                    .border(
                                        width = 1.dp,
                                        color = customBorderColor,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Text(
                                    "Custom",
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                )
                            }
                        }

                    }


                }
            }


            item {
                Box(modifier = Modifier.height(50.dp))
            }

            item {
                Text(
                    text = "Unit Style",
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

            item {
                Box(modifier = Modifier.height(30.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(horizontal = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .bounceClick(.9f)
                            {
                                selectedUnitStyle = 0
                            }
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .border(
                                width = 1.5.dp,
                                color = unitColor1,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Long",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Box(modifier = Modifier.weight(.08f))
                    Box(
                        modifier = Modifier
                            .bounceClick(.9f)
                            {
                                selectedUnitStyle = 1
                            }
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .border(
                                width = 1.5.dp,
                                color = unitColor2,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Short", style = TextStyle(
                                fontSize = 12.sp, color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Box(modifier = Modifier.weight(.08f))
                    Box(
                        modifier = Modifier
                            .bounceClick(.9f)
                            {
                                selectedUnitStyle = 2
                            }
                            .fillMaxHeight()
                            .weight(1f)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .border(
                                width = 1.5.dp,
                                color = unitColor3,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Narrow",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            item {
                Box(modifier = Modifier.height(100.dp))
            }





            item {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                {
                    Button(
                        onClick = {

                            val droidTime = DroidTime()
                            if (selectedTab == 0) {
                                response = droidTime.relativeLocalisedDateTimeFormatter(
                                    comparator = LocalDateTime(
                                        year = instant.year,
                                        monthNumber = instant.monthNumber,
                                        dayOfMonth = instant.dayOfMonth,
                                        hour = instantHour,
                                        minute = instantMinute,
                                        second = 0,
                                        nanosecond = 0
                                    ).toInstant(TimeZone.currentSystemDefault()),
                                    instant = LocalDateTime(
                                        year = now.year,
                                        monthNumber = now.monthNumber,
                                        dayOfMonth = now.dayOfMonth,
                                        hour = nowHour,
                                        minute = nowMinute,
                                        second = 0,
                                        nanosecond = 0
                                    ).toInstant(TimeZone.currentSystemDefault()),
                                    unitPattern = when (viewModel.patternSelected.value) {
                                        is PatternSelection.Custom -> {

                                            val list: MutableList<RelativeLocalisedUnit> =
                                                mutableListOf()
                                            viewModel.patternList.forEach { pattern ->
                                                RelativeLocalisedUnit.entries.forEach { unit ->
                                                    if (pattern.selected) {
                                                        if (unit.name == pattern.name) {
                                                            list.add(unit)
                                                        }
                                                    }
                                                }
                                            }
                                            RelativeUnitPattern.Custom(
                                                relativeUnits = list
                                            )
                                        }

                                        is PatternSelection.Default -> {
                                            RelativeUnitPattern.Default
                                        }
                                    },
                                    unitStyle = when (selectedUnitStyle) {
                                        0 -> UnitStyle.Long
                                        1 -> UnitStyle.Short
                                        else -> UnitStyle.Narrow
                                    }
                                )
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        ),
                        modifier = Modifier
                            .bounceClick(bounce = .9f)
                            {


                            }
                            .fillMaxWidth()

                    ) {
                        Box(modifier = Modifier.size(20.dp))
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.sparkle),
                                contentDescription = ""
                            )
                        }

                        Text(
                            text = "Generate",
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(11.dp)
                        )
                    }
                }

            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(100.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                topStart = 30.dp,
                                topEnd = 0.dp,
                                bottomEnd = 30.dp,
                                bottomStart = 30.dp
                            )
                        )
                )
                {
                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxHeight(.6f)
                                .width(5.dp)
                                .background(Color.Black, RoundedCornerShape(10.dp))
                        )

                        Spacer(modifier = Modifier.width(30.dp))

                        Text(
                            text = response,
                            modifier = Modifier.animateContentSize(
                                spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                    stiffness = Spring.StiffnessVeryLow
                                )
                            ),
                            style = TextStyle(
                                fontSize = 30.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(150.dp))
            }
        }
    }


}

fun Modifier.bounceClick(bounce: Float, active: Boolean = true, onClick: () -> Unit) = composed {

    var buttonState by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        if (buttonState) bounce else 1f,
        label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            enabled = active,
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState) {
                    waitForUpOrCancellation()
                    false
                } else {
                    awaitFirstDown(false)
                    true
                }
            }
        }


}