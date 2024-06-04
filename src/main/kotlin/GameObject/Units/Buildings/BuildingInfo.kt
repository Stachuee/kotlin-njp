package GameObject.Units.Buildings

import org.openrndr.math.IntVector2

object BuildingInfo {

    data class BuildingInfo(val cost : IntVector2, val weight : Double, val weightChange : Double)

    val info : Map<BuildingEnum, BuildingInfo> = mapOf(
        BuildingEnum.HOUSE to BuildingInfo(IntVector2(5,5), 200.0, -20.0),
        BuildingEnum.FARM to BuildingInfo(IntVector2(5,0), 150.0, -15.0),
        BuildingEnum.MINE to BuildingInfo(IntVector2(0,5), 150.0, -15.0),
        BuildingEnum.WAREHOUSE to BuildingInfo(IntVector2(10,10), 50.0, -10.0),
        BuildingEnum.TOWER to BuildingInfo(IntVector2(15,5), 100.0, -20.0)
        )

}