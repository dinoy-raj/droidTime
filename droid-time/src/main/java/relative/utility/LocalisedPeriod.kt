package relative.utility

/**
 *  Localised Period represents combination of Relative unit and count
 */
data class LocalisedPeriod(

    /**
     *  Relative Localised Unit
     */
    val unit: RelativeLocalisedUnit,

    /**
     * amount of Relative unit exist
     */
    val count: Int

)
