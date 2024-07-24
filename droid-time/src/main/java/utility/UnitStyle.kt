package utility

/**
 * Represents verbosity of unit style
 */
sealed class UnitStyle {

    /**
     *  Highest Verbosity of Units
     */
    data object Long : UnitStyle()

    /**
     * Short Verbosity of Units
     */
    data object Short : UnitStyle()

    /**
     * Narrow Verbosity of Units
     */
    data object Narrow : UnitStyle()

}