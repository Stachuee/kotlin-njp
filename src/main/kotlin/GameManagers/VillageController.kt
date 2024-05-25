package GameManagers

import GameObject.Units.Buildings.BuildingEnum
import GameObject.Units.Buildings.BuildingFarm
import GameObject.Units.Buildings.BuildingMine
import GameObject.Units.Buildings.BuildingTavern
import GameObject.Units.Heroes.HeroStates
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.Heroes.Types.Pesant
import GameObject.Units.Heroes.Types.WarriorBase
import GameObject.Units.UnitController
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

object VillageController : IUpdate {

    val pesantList : MutableList<Pesant> = mutableListOf()
    val wariors : MutableList<WarriorBase> = mutableListOf()

    val tavern : BuildingTavern = BuildingManager.buildBuilding(BuildingEnum.TAVERN, RandomUtils.getPointInCircle(100.0), false) as BuildingTavern

    fun createVillage(){
        createStartingBuildings()
        createStartingPopulation()
    }

    fun createStartingBuildings(){

        val maxSize = Math.min(MapController.mapSize.x, MapController.mapSize.y) / 2
        for(i in 1..10) BuildingManager.buildBuilding(BuildingEnum.MINE, RandomUtils.getPointInCircle(maxSize), false)
        for(i in 1..2) BuildingManager.buildBuilding(BuildingEnum.FARM, RandomUtils.getPointInCircle(750.0), false)
    }

    fun createStartingPopulation(){
        for(i in 1..3) HeroesBuilder.placeHero(HeroesEnum.PEASANT, RandomUtils.getPointInCircle(100.0) )
    }

    override fun update() {

    }


}