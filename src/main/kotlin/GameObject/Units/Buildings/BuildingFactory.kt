package GameObject.Units.Buildings

import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

object BuildingFactory {

    fun buildBuilding(building : BuildingEnum, position: Vector2) : BuildingBase {
        when(building)
        {
            BuildingEnum.HOUSE -> return BuildingHouse().setUnitPosition(GameCamera.screenToWorldPosition(position)) as BuildingBase
            BuildingEnum.TAVERN -> return BuildingTavern().setUnitPosition(GameCamera.screenToWorldPosition(position)) as BuildingBase
            BuildingEnum.MINE -> return BuildingMine().setUnitPosition(GameCamera.screenToWorldPosition(position)) as BuildingBase
            BuildingEnum.FARM -> return BuildingFarm().setUnitPosition(GameCamera.screenToWorldPosition(position)) as BuildingBase
        }
    }

    private fun build(position: Vector2, atlasPosition: Vector2) : BuildingBase {
        val building = BuildingHouse().setUnitPosition(GameCamera.screenToWorldPosition(position)) as BuildingBase

        return building
    }
}