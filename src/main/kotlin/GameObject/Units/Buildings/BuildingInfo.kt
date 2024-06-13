package GameObject.Units.Buildings

import org.openrndr.math.IntVector2

object BuildingInfo {

    data class BuildingInfo(val cost : IntVector2, val weight : Double, val weightChange : Double)

    val info : Map<BuildingEnum, BuildingInfo> = mapOf(
        BuildingEnum.HOUSE to BuildingInfo(IntVector2(5,5), 180.0, -15.0),
        BuildingEnum.FARM to BuildingInfo(IntVector2(10,0), 175.0, -15.0),
        BuildingEnum.MINE to BuildingInfo(IntVector2(0,10), 50.0, -15.0),
        BuildingEnum.WAREHOUSE to BuildingInfo(IntVector2(10,10), 50.0, -10.0),

        BuildingEnum.TOWER to BuildingInfo(IntVector2(10,5), 150.0, -10.0),
        BuildingEnum.ARENA to BuildingInfo(IntVector2(15,10), 120.0, -10.0),
        BuildingEnum.CHAPEL to BuildingInfo(IntVector2(15,20), 130.0, -10.0),
        BuildingEnum.CASTLE to BuildingInfo(IntVector2(65,40), 90.0, -10.0),
        BuildingEnum.SHRINE to BuildingInfo(IntVector2(100,100), 80.0, -10.0),
        BuildingEnum.TREEHOUSE to BuildingInfo(IntVector2(20,40), 110.0, -10.0),
        )

}