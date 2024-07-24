package relative.utility

/**
 * Represents Pattern of Relative Units - List of [RelativeLocalisedUnit]
 */
sealed class RelativeUnitPattern {

    /**
     * Default list of [RelativeLocalisedUnit] ordered based on priority
     */
    data object Default : RelativeUnitPattern()


    /**
     *  Custom selected List of [RelativeLocalisedUnit]
     */
    data class Custom(val relativeUnits: List<RelativeLocalisedUnit> =
        RelativeLocalisedUnit.entries) : RelativeUnitPattern()

}