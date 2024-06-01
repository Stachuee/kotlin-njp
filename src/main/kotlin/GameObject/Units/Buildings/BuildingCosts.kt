package GameObject.Units.Buildings

import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2

object BuildingCosts {

    val costs : Map<BuildingEnum, IntVector2> = mapOf(
        BuildingEnum.HOUSE to IntVector2(5,5),
        BuildingEnum.FARM to IntVector2(1,0)
        )

}