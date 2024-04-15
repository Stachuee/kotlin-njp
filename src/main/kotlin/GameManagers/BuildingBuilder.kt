package GameManagers

import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Buildings.BuildingHouse
import GameObject.Units.UnitController
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import InputSystem.InputController
import InputSystem.MouseInterface
import org.openrndr.MouseButton
import org.openrndr.math.Vector2

class BuildingBuilder private constructor() : MouseInterface {
    //<editor-fold desc="Singleton">
    companion object {

        @Volatile
        private var instance: BuildingBuilder? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: BuildingBuilder().also { instance = it }
            }
    }
    //</editor-fold>

    fun buildBuilding(building : BuildingBase) {
        UnitController.getInstance().addUnit(building)
    }

    override fun mouseDown(event: MouseButton) {

        buildBuilding(BuildingHouse(
            ObjectRenderer("buildings",
                Vector2(0.0, 0.0), 0)
        ).setUnitPosition(
            GameCamera.getInstance().screenToWorldPosition(InputController.getInstance().getScreenMousePosition())) as BuildingBase)
    }

    override fun mouseUp(event: MouseButton) {

    }

}