package utility

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import relative.utility.RelativeUnitPattern
import java.time.Instant

/**
 * Collection of Relative and Absolute Date Time Formatters
 */
interface TimeFormatters {

    /** Relative Localised Date Time Formatters */
    fun relativeLocalisedDateTimeFormatter(
        comparator: kotlinx.datetime.Instant,
        instant: kotlinx.datetime.Instant,
        formattingPattern: RelativeUnitPattern = RelativeUnitPattern.Default,
        unitStyle: UnitStyle = UnitStyle.Long,
        timeZone: TimeZone
    ): String

    fun relativeLocalisedDateTimeFormatter(
        comparator: Instant,
        instant: Instant,
        formattingPattern: RelativeUnitPattern = RelativeUnitPattern.Default,
        unitStyle: UnitStyle = UnitStyle.Long,
        timeZone: TimeZone
    ): String

    fun relativeLocalisedDateTimeFormatter(
        comparator: Long,
        instant: Long,
        formattingPattern: RelativeUnitPattern = RelativeUnitPattern.Default,
        unitStyle: UnitStyle = UnitStyle.Long,
        timeZone: TimeZone
    ): String

    fun relativeLocalisedDateTimeFormatter(
        comparator: LocalDateTime,
        instant: LocalDateTime,
        formattingPattern: RelativeUnitPattern = RelativeUnitPattern.Default,
        unitStyle: UnitStyle = UnitStyle.Long,
        timeZone: TimeZone
    ): String

    fun relativeLocalisedDateTimeFormatter(
        comparator: java.time.LocalDateTime,
        instant: java.time.LocalDateTime,
        formattingPattern: RelativeUnitPattern = RelativeUnitPattern.Default,
        unitStyle: UnitStyle = UnitStyle.Long,
        timeZone: TimeZone
    ): String


}