package relative.helpers

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.until
import relative.utility.LocalisedPeriod
import relative.utility.RelativeLocalisedUnit
import relative.utility.RelativeUnitPattern

/**
 * Find Highest Localised Format Period between given instants
 *
 * ######
 * @param [comparator] compared instance of time
 * @param [timeZone] time zone for processing local date time / instant given
 * @param [formattingPattern] list of relative localised units
 *
 * @return Highest Localised Format period between given instants, null if nothing exist
 */
internal fun Instant.localisedPeriodBetween(
    comparator: Instant,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    formattingPattern: RelativeUnitPattern
): LocalisedPeriod? {

    // sorting based on pattern and priority
    val units = when (formattingPattern) {
        is RelativeUnitPattern.Custom -> formattingPattern.relativeUnits.sortedBy { it.ordinal }
        RelativeUnitPattern.Default -> RelativeLocalisedUnit.entries
    }

    // period calculation between instants
    units.forEach { unit ->
        val relativeUnit = relativeToDateTimeMapping(unit)

        relativeUnit?.let {
            val count = this.until(comparator, relativeUnit, timeZone).toInt()
            if (count != 0) return LocalisedPeriod(unit = unit, count = count)
        }
    }
    return null
}


fun relativeToDateTimeMapping(unit: RelativeLocalisedUnit): DateTimeUnit? {
    return when (unit) {
        RelativeLocalisedUnit.Year -> DateTimeUnit.YEAR
        RelativeLocalisedUnit.Month -> DateTimeUnit.MONTH
        RelativeLocalisedUnit.Week -> DateTimeUnit.WEEK
        RelativeLocalisedUnit.Day -> DateTimeUnit.DAY
        RelativeLocalisedUnit.Hour -> DateTimeUnit.HOUR
        RelativeLocalisedUnit.Minute -> DateTimeUnit.MINUTE
        RelativeLocalisedUnit.Second -> DateTimeUnit.SECOND
    }
}