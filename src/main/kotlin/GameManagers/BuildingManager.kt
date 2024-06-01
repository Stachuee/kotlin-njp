package GameManagers

import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Buildings.BuildingEnum
import GameObject.Units.Buildings.BuildingFactory
import GameObject.Units.Buildings.BuildingHouse
import GameObject.Units.UnitController
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import InputSystem.IMouseButton
import InputSystem.InputController
import org.openrndr.MouseButton
import org.openrndr.math.Vector2

object BuildingManager : IMouseButton, IUpdate  {

    var ghostObject : BuildingBase? = null
    var showGhost : Boolean = false

    fun showGhost(building: BuildingEnum) {
        buildBuilding(building, GameCamera.screenToWorldPosition(InputController.getScreenMousePosition()))
    }


    fun buildBuilding(building : BuildingEnum, position: Vector2) : BuildingBase {
        var build = BuildingFactory.buildBuilding(building, position)

        addBuildingUnit(build)
        build.setActive(true)

        return build
    }

    fun addBuildingUnit(building : BuildingBase) {
        UnitController.addUnit(building)
    }

    fun destroyBuilding(building : BuildingBase) {
        UnitController.removeUnit(building)
        building.renderer.removeRenderer()
    }

    override fun mouseDown(event: MouseButton) {
        when(event)
        {
            MouseButton.RIGHT ->
                if(ghostObject != null)
                {
                    destroyBuilding(ghostObject!!)
                }
            MouseButton.LEFT ->
                if(ghostObject != null)
                {
                    ghostObject!!.setActive(true)
                    ghostObject = null
                }
            else -> {}
        }

    }

    override fun mouseUp(event: MouseButton) {

    }

    override fun update() {
        if(ghostObject != null)
        {
            val worldPos = GameCamera.screenToWorldPosition(InputController.getScreenMousePosition())
            ghostObject!!.setWorldPosition(worldPos)
            ghostObject!!.renderer.sortingLayer = (-worldPos.y).toInt()
        }
    }


}