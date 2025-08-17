<h1 align="center"> droid time </h1>

<p align="center">
  <a href="https://maven-badges.herokuapp.com/maven-central/io.github.dinoy-raj/droid-time"><img src="https://img.shields.io/maven-central/v/io.github.dinoy-raj/droid-time?color=fedcba" alt="Maven Central"></a>
</p>

Droid Time is an Android library for formatting time relatively and absolutely, generating locale-specific messages. It simplifies the way you handle time differences in your Android applications, providing human-readable strings like "in 2 hours" or "5 months ago".

## Features

- **Relative Time Formatting**: Automatically formats time differences into easy-to-understand relative strings.
- **Locale Aware**: Generates messages based on the device's default locale.
- **Multiple Time Representations**: Supports `kotlinx.datetime.Instant`, `java.time.Instant`, `Long` (epoch milliseconds), `kotlinx.datetime.LocalDateTime`, and `java.time.LocalDateTime`.
- **Customizable Units**: Control the verbosity and the set of time units used for formatting.

## Installation

Add the dependency to your module-level `build.gradle` or `build.gradle.kts` file:

```gradle
dependencies {
    implementation("io.github.dinoy-raj:droid-time:1.0.0")
}
```

## Usage

Create an instance of `DroidTime` and use the `relativeLocalisedDateTimeFormatter` method to format your time values.

```kotlin
val droidTime = DroidTime()
```

### Formatting `kotlinx.datetime.Instant`

```kotlin
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days

val now = Clock.System.now()
val fiveDaysAgo = now - 5.days

val relativeTime = droidTime.relativeLocalisedDateTimeFormatter(
    comparator = fiveDaysAgo,
    instant = now
)
// Result: "5 days ago"
```

### Formatting `java.time.Instant`

```kotlin
import java.time.Instant
import java.time.temporal.ChronoUnit

val now = Instant.now()
val twoHoursAgo = now.minus(2, ChronoUnit.HOURS)

val relativeTime = droidTime.relativeLocalisedDateTimeFormatter(
    comparator = twoHoursAgo,
    instant = now
)
// Result: "2 hours ago"
```

### Formatting `Long` (Epoch Milliseconds)

```kotlin
val now = System.currentTimeMillis()
val tenMinutesAgo = now - (10 * 60 * 1000)

val relativeTime = droidTime.relativeLocalisedDateTimeFormatter(
    comparator = tenMinutesAgo,
    instant = now
)
// Result: "10 minutes ago"
```

### Customizing Unit Style

You can control the verbosity of the output using `UnitStyle`.

- `UnitStyle.LONG`: "5 months ago"
- `UnitStyle.SHORT`: "5 mo. ago"
- `UnitStyle.NARROW`: "5 mo. ago" (may vary by locale)

```kotlin
val relativeTime = droidTime.relativeLocalisedDateTimeFormatter(
    comparator = fiveDaysAgo,
    instant = now,
    unitStyle = UnitStyle.SHORT
)
// Result: "5 days ago" (Note: Android's formatter might not always have shorter versions for all units)
```

### Customizing Unit Pattern

You can specify which time units to use for formatting with `RelativeUnitPattern`.

```kotlin
import relative.utility.RelativeLocalisedUnit

val customPattern = RelativeUnitPattern.Custom(
    listOf(
        RelativeLocalisedUnit.YEAR,
        RelativeLocalisedUnit.MONTH,
        RelativeLocalisedUnit.WEEK
    )
)

val relativeTime = droidTime.relativeLocalisedDateTimeFormatter(
    comparator = fiveDaysAgo,
    instant = now,
    unitPattern = customPattern
)
// Result: "Last week"
```


