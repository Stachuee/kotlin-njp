package GameManagers

import GameManagers.VillageController.vilageRange
import GameObject.Units.Buildings.*
import GameObject.Units.UnitController
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import InputSystem.IMouseButton
import InputSystem.InputController
import Utils.RandomUtils
import org.openrndr.MouseButton
import org.openrndr.math.Vector2

object BuildingManager : IMouseButton, IUpdate  {

    var ghostObject : BuildingBase? = null
    var showGhost : Boolean = false

    var buildingSpots = mutableListOf<Vector2>()

    fun showGhost(building: BuildingEnum) {
        buildBuilding(building, GameCamera.screenToWorldPosition(InputController.getScreenMousePosition()))
    }


    fun buildBuilding(building : BuildingEnum, position: Vector2) : BuildingBase {
        var build = BuildingFactory.buildBuilding(building, position)

        buildingSpots.add(position)
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

    fun getBuildingCount(building : BuildingEnum) : Int {
        when(building)
        {
            BuildingEnum.HOUSE -> return BuildingHouse.allHouses.size
            BuildingEnum.WAREHOUSE -> return BuildingWarehouse.allWarehouses.size
            BuildingEnum.MINE -> return BuildingMine.allMines.size
            BuildingEnum.FARM -> return BuildingFarm.allFarms.size
            BuildingEnum.TOWER -> return BuildingTower.allTowers.size
        }
    }

    fun findBuildingSpot() : Vector2{
        var iterations = 0
        var pos : Vector2
        do{
            if(iterations > 20) VillageController.expandVillage()
            pos = RandomUtils.getPointInCircle(VillageController.vilageRange)
            iterations++
        } while (buildingSpots.any { Math.abs(it.x - pos.x) > 160.0 && Math.abs(it.y - pos.y) > 160.0 })
        return pos
    }
}