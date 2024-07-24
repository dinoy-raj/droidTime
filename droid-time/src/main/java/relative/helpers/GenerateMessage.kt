package relative.helpers

import android.icu.text.DisplayContext
import android.icu.text.NumberFormat
import android.icu.text.RelativeDateTimeFormatter
import android.icu.util.ULocale
import relative.utility.LocalisedPeriod
import relative.utility.RelativeLocalisedUnit
import utility.UnitStyle
import java.util.Locale
import kotlin.math.absoluteValue

/**
 * Generate localised message from a given [LocalisedPeriod]
 * ######
 * @param [unitStyle] unit style for controlling verbosity of unit
 * @return Localised Formatted Message
 */
fun LocalisedPeriod.generateLocalisedMessage(unitStyle: UnitStyle): String {

    // Relative Date Time Formatter
    val fmt = RelativeDateTimeFormatter.getInstance(
        ULocale.getDefault(), NumberFormat.getInstance(Locale.getDefault()),
        unitStyle.map(), DisplayContext.CAPITALIZATION_NONE
    )

    var formattedMessage = ""

    if (this.count > 0) {
        formattedMessage += fmt.format(
            this.count.toDouble(),
            RelativeDateTimeFormatter.Direction.NEXT,
            this.unit.map()
        )
    } else if (this.count < 0) {
        formattedMessage += fmt.format(
            this.count.toDouble().absoluteValue,
            RelativeDateTimeFormatter.Direction.LAST,
            this.unit.map()
        )
    }

    return formattedMessage

}


/**
 * Maps Relative Localised Unit to corresponding Relative Unit
 * @see [RelativeLocalisedUnit]
 * @see [RelativeDateTimeFormatter.RelativeUnit]
 */
fun RelativeLocalisedUnit.map(): RelativeDateTimeFormatter.RelativeUnit {
    return when (this) {
        RelativeLocalisedUnit.Year -> RelativeDateTimeFormatter.RelativeUnit.YEARS
        RelativeLocalisedUnit.Month -> RelativeDateTimeFormatter.RelativeUnit.MONTHS
        RelativeLocalisedUnit.Week -> RelativeDateTimeFormatter.RelativeUnit.WEEKS
        RelativeLocalisedUnit.Hour -> RelativeDateTimeFormatter.RelativeUnit.HOURS
        RelativeLocalisedUnit.Minute -> RelativeDateTimeFormatter.RelativeUnit.MINUTES
        RelativeLocalisedUnit.Second -> RelativeDateTimeFormatter.RelativeUnit.SECONDS
        RelativeLocalisedUnit.Day -> RelativeDateTimeFormatter.RelativeUnit.DAYS
    }
}


/**
 * Maps Unit style to corresponding Relative Date Formatter Style
 * @see [UnitStyle]
 * @see [RelativeDateTimeFormatter.Style]
 */
fun UnitStyle.map(): RelativeDateTimeFormatter.Style {
    return when (this) {
        UnitStyle.Long -> RelativeDateTimeFormatter.Style.LONG
        UnitStyle.Narrow -> RelativeDateTimeFormatter.Style.NARROW
        UnitStyle.Short -> RelativeDateTimeFormatter.Style.SHORT
    }
}
