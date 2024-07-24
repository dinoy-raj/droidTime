import android.icu.text.RelativeDateTimeFormatter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toKotlinLocalDateTime
import relative.helpers.generateLocalisedMessage
import relative.helpers.localisedPeriodBetween
import relative.utility.RelativeLocalisedUnit
import relative.utility.RelativeUnitPattern
import utility.TimeFormatters
import utility.UnitStyle
import java.time.ZoneId


/**
 * [DroidTime]  class contains methods which are used to format time
 * (whether kotlin instant or java instant or local date time)
 * relatively and absolutely and generate default locale specific message
 *
 * - for Relative formatting [relativeLocalisedDateTimeFormatter] Eg. in 2 hours, 2 months ago
 *
 */
class DroidTime : TimeFormatters {


    /**
     * Generate localised message for relative date time difference between [comparator] with respect to [instant].
     *
     * eg:  in 5 days, 1 week ago, 2 months ago, 30 min ago, now, etc.
     *
     *
     *  **Unit style** used to control verbosity of Units while formatting
     *  Eg:
     *
     *  - Long  *5 months*
     *  - Narrow  *5 mo*
     *
     *  Opt in specific units by switching **units pattern** to Custom, by default it format based on [RelativeLocalisedUnit]
     *
     *  This Api intended create formatted message based on highest [RelativeLocalisedUnit] exist in between [comparator] and [instant]
     *  it does not produce combination of Units like "in 5 days, 2 hour, 30 minutes".
     *
     *  Utilise absoluteDateTimeFormatter for such use case.
     * ######
     *
     * @param [comparator]  kotlin Instant of time to which instant is compared.
     *
     * @param [instant]  kotlin Instant of time with respect to comparator compared.
     *
     * @param [unitPattern] either default unit set or custom (user selected list of units).
     *
     * @param [unitStyle] Formatting style of Units, controls verbosity of unit string.
     *
     * @param [timeZone] time zone with respect to which date time processed, by default system timezone.
     *
     *
     * @return  Localised [specific to device default locale] message,
     * empty string if localised representation does not exist.
     *
     */
    override fun relativeLocalisedDateTimeFormatter(
        comparator: Instant,
        instant: Instant,
        unitPattern: RelativeUnitPattern,
        unitStyle: UnitStyle,
        timeZone: TimeZone
    ): String {

        val relativeDateTimeFormatter = RelativeDateTimeFormatter.getInstance()

        if (instant == Clock.System.now()) {
            return relativeDateTimeFormatter.format(
                RelativeDateTimeFormatter.Direction.PLAIN,
                RelativeDateTimeFormatter.AbsoluteUnit.NOW
            )
        }

        return instant.localisedPeriodBetween(comparator, timeZone, unitPattern)
            ?.generateLocalisedMessage(unitStyle)
            ?: ""
    }


    /**
     * Generate localised message for relative date time difference between [comparator] with respect to [instant].
     *
     * eg:  in 5 days, 1 week ago, 2 months ago, 30 min ago, now, etc.
     *
     *
     *  **Unit style** used to control verbosity of Units while formatting
     *  Eg:
     *
     *  - Long  *5 months*
     *  - Narrow  *5 mo*
     *
     *  Opt in specific units by switching **units pattern** to Custom, by default it format based on [RelativeLocalisedUnit]
     *
     *  This Api intended create formatted message based on highest [RelativeLocalisedUnit] exist in between [comparator] and [instant]
     *  it does not produce combination of Units like "in 5 days, 2 hour, 30 minutes".
     *
     *  Utilise absoluteDateTimeFormatter for such use case.
     * ######
     *
     * @param [comparator]  java Instant of time to which instant is compared.
     *
     * @param [instant]  java Instant of time with respect to comparator compared.
     *
     * @param [unitPattern] either default unit set or custom (user selected list of units).
     *
     * @param [unitStyle] Formatting style of Units, controls verbosity of unit string.
     *
     * @param [timeZone] time zone with respect to which date time processed, by default system timezone.
     *
     *
     * @return  Localised [specific to device default locale] message,
     * empty string if localised representation does not exist.
     *
     */
    override fun relativeLocalisedDateTimeFormatter(
        comparator: java.time.Instant,
        instant: java.time.Instant,
        unitPattern: RelativeUnitPattern,
        unitStyle: UnitStyle,
        timeZone: TimeZone
    ): String {
        return relativeLocalisedDateTimeFormatter(
            comparator = comparator.toKotlinInstant(),
            instant = instant.toKotlinInstant(),
            unitPattern = unitPattern,
            unitStyle = unitStyle,
            timeZone = timeZone
        )
    }


    /**
     * Generate localised message for relative date time difference between [comparator] with respect to [instant].
     *
     * eg:  in 5 days, 1 week ago, 2 months ago, 30 min ago, now, etc.
     *
     *
     *  **Unit style** used to control verbosity of Units while formatting
     *  Eg:
     *
     *  - Long  *5 months*
     *  - Narrow  *5 mo*
     *
     *  Opt in specific units by switching **units pattern** to Custom, by default it format based on [RelativeLocalisedUnit]
     *
     *  This Api intended create formatted message based on highest [RelativeLocalisedUnit] exist in between [comparator] and [instant]
     *  it does not produce combination of Units like "in 5 days, 2 hour, 30 minutes".
     *
     *  Utilise absoluteDateTimeFormatter for such use case.
     * ######
     *
     * @param [comparator]  Epoch milli of time to which instant is compared.
     *
     * @param [instant]  Epoch milli of time with respect to comparator compared.
     *
     * @param [unitPattern] either default unit set or custom (user selected list of units).
     *
     * @param [unitStyle] Formatting style of Units, controls verbosity of unit string.
     *
     * @param [timeZone] time zone with respect to which date time processed, by default system timezone.
     *
     *
     * @return  Localised [specific to device default locale] message,
     * empty string if localised representation does not exist.
     *
     */
    override fun relativeLocalisedDateTimeFormatter(
        comparator: Long,
        instant: Long,
        unitPattern: RelativeUnitPattern,
        unitStyle: UnitStyle,
        timeZone: TimeZone
    ): String {
        return relativeLocalisedDateTimeFormatter(
            comparator = java.time.Instant.ofEpochMilli(comparator).atZone(ZoneId.systemDefault())
                .toInstant(),
            instant = java.time.Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault())
                .toInstant(),
            unitPattern = unitPattern,
            unitStyle = unitStyle,
            timeZone = timeZone
        )
    }


    /**
     * Generate localised message for relative date time difference between [comparator] with respect to [instant].
     *
     * eg:  in 5 days, 1 week ago, 2 months ago, 30 min ago, now, etc.
     *
     *
     *  **Unit style** used to control verbosity of Units while formatting
     *  Eg:
     *
     *  - Long  *5 months*
     *  - Narrow  *5 mo*
     *
     *  Opt in specific units by switching **units pattern** to Custom, by default it format based on [RelativeLocalisedUnit]
     *
     *  This Api intended create formatted message based on highest [RelativeLocalisedUnit] exist in between [comparator] and [instant]
     *  it does not produce combination of Units like "in 5 days, 2 hour, 30 minutes".
     *
     *  Utilise absoluteDateTimeFormatter for such use case.
     * ######
     *
     * @param [comparator]  Kotlin LocalDateTime of time to which instant is compared.
     *
     * @param [instant]  Kotlin LocalDateTime of time with respect to comparator compared.
     *
     * @param [unitPattern] either default unit set or custom (user selected list of units).
     *
     * @param [unitStyle] Formatting style of Units, controls verbosity of unit string.
     *
     * @param [timeZone] time zone with respect to which date time processed, by default system timezone.
     *
     *
     * @return  Localised [specific to device default locale] message,
     * empty string if localised representation does not exist.
     *
     */
    override fun relativeLocalisedDateTimeFormatter(
        comparator: LocalDateTime,
        instant: LocalDateTime,
        unitPattern: RelativeUnitPattern,
        unitStyle: UnitStyle,
        timeZone: TimeZone
    ): String {
        return relativeLocalisedDateTimeFormatter(
            comparator = comparator.toInstant(TimeZone.currentSystemDefault()),
            instant = instant.toInstant(TimeZone.currentSystemDefault()),
            unitPattern = unitPattern,
            unitStyle = unitStyle,
            timeZone = timeZone
        )
    }


    /**
     * Generate localised message for relative date time difference between [comparator] with respect to [instant].
     *
     * eg:  in 5 days, 1 week ago, 2 months ago, 30 min ago, now, etc.
     *
     *
     *  **Unit style** used to control verbosity of Units while formatting
     *  Eg:
     *
     *  - Long  *5 months*
     *  - Narrow  *5 mo*
     *
     *  Opt in specific units by switching **units pattern** to Custom, by default it format based on [RelativeLocalisedUnit]
     *
     *  This Api intended create formatted message based on highest [RelativeLocalisedUnit] exist in between [comparator] and [instant]
     *  it does not produce combination of Units like "in 5 days, 2 hour, 30 minutes".
     *
     *  Utilise absoluteDateTimeFormatter for such use case.
     * ######
     *
     * @param [comparator] Java LocalDateTime of time to which instant is compared.
     *
     * @param [instant]  Java LocalDateTime of time with respect to comparator compared.
     *
     * @param [unitPattern] either default unit set or custom (user selected list of units).
     *
     * @param [unitStyle] Formatting style of Units, controls verbosity of unit string.
     *
     * @param [timeZone] time zone with respect to which date time processed, by default system timezone.
     *
     *
     * @return  Localised [specific to device default locale] message,
     * empty string if localised representation does not exist.
     *
     */
    override fun relativeLocalisedDateTimeFormatter(
        comparator: java.time.LocalDateTime,
        instant: java.time.LocalDateTime,
        unitPattern: RelativeUnitPattern,
        unitStyle: UnitStyle,
        timeZone: TimeZone
    ): String {
        return relativeLocalisedDateTimeFormatter(
            comparator = comparator.toKotlinLocalDateTime(),
            instant = comparator.toKotlinLocalDateTime(),
            unitPattern = unitPattern,
            unitStyle = unitStyle,
            timeZone = timeZone
        )
    }
}