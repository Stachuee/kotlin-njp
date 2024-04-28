package GameObject.Units.Buildings

import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

object BuildingFactory {

    fun buildBuilding(building : BuildingEnum, position: Vector2) : BuildingBase {
        when(building)
        {
            BuildingEnum.HOUSE -> return build(position, Vector2(0.0, 0.0))
            BuildingEnum.TAVERN -> return  build(position, Vector2(0.0, 1.0))
        }
    }

    private fun build(position: Vector2, atlasPosition: Vector2) : BuildingBase {
        val building = BuildingHouse(
            ObjectRenderer(
                "buildings",
                atlasPosition, 0
            )
        ).setUnitPosition(
            GameCamera.screenToWorldPosition(position)
        ) as BuildingBase

        return building
    }
}