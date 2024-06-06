package GameObject.Units.Buildings

import GameObject.Units.Buildings.ProductionBuildings.BuildingFarm
import GameObject.Units.Buildings.ProductionBuildings.BuildingHouse
import GameObject.Units.Buildings.ProductionBuildings.BuildingMine
import GameObject.Units.Buildings.ProductionBuildings.BuildingWarehouse
import GameObject.Units.Buildings.UnitBuildings.*
import GameRenderer.GameCamera
import org.openrndr.math.Vector2

object BuildingFactory {

    fun buildBuilding(building : BuildingEnum, position: Vector2) : BuildingBase {
        when(building)
        {
            BuildingEnum.HOUSE -> return BuildingHouse().setUnitPosition(position) as BuildingBase
            BuildingEnum.WAREHOUSE -> return BuildingWarehouse().setUnitPosition(position) as BuildingBase
            BuildingEnum.MINE -> return BuildingMine().setUnitPosition(position) as BuildingBase
            BuildingEnum.FARM -> return BuildingFarm().setUnitPosition(position) as BuildingBase
            BuildingEnum.TOWER -> return BuildingTower().setUnitPosition(position) as BuildingBase
            BuildingEnum.ARENA -> return BuildingArena().setUnitPosition(position) as BuildingBase
            BuildingEnum.CASTLE -> return BuildingCastle().setUnitPosition(position) as BuildingBase
            BuildingEnum.CHAPEL -> return BuildingChapel().setUnitPosition(position) as BuildingBase
            BuildingEnum.SHRINE -> return BuildingShrine().setUnitPosition(position) as BuildingBase
            BuildingEnum.TREEHOUSE -> return BuildingTreehouse().setUnitPosition(position) as BuildingBase
        }
    }

    private fun build(position: Vector2, atlasPosition: Vector2) : BuildingBase {
        val building = BuildingHouse().setUnitPosition(GameCamera.screenToWorldPosition(position)) as BuildingBase

        return building
    }
}