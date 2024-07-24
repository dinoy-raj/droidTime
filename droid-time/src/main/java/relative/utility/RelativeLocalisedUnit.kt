package relative.utility

/**
 *  Represents Relative Units For Localised Relative Formatter
 */
enum class RelativeLocalisedUnit(priority: Int) {

    /**
     * Year
     */
    Year(7),

    /**Month*/
    Month(6),

    /**
     * Week
     */
    Week(5),

    /**
     * Day
     */
    Day(4),

    /**
     * Hour
     */
    Hour(3),

    /**
     * Minute
     */
    Minute(2),

    /**
     * Second
     */
    Second(1),

}