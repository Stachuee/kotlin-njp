package GameManagers

import GameObject.Units.Buildings.BuildingEnum
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.UnitController
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

object VillageController {

    fun createVillage(){
        createStartingBuildings()
        createStartingPopulation()
    }

    fun createStartingBuildings(){
        BuildingManager.buildBuilding(BuildingEnum.TAVERN, RandomUtils.getPointInCircle(100.0), false)
        val maxSize = Math.min(MapController.mapSize.x, MapController.mapSize.y) / 2
        for(i in 1..10) BuildingManager.buildBuilding(BuildingEnum.MINE, RandomUtils.getPointInCircle(maxSize), false)
        for(i in 1..2) BuildingManager.buildBuilding(BuildingEnum.FARM, RandomUtils.getPointInCircle(750.0), false)
    }

    fun createStartingPopulation(){
        for(i in 1..3) HeroesBuilder.placeHero(HeroesEnum.PEASANT, RandomUtils.getPointInCircle(100.0) )
    }

}