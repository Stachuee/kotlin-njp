package GameManagers

import GameObject.Units.Buildings.*
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

    val tavern : BuildingTavern = BuildingManager.buildBuilding(BuildingEnum.TAVERN, RandomUtils.getPointInCircle(100.0)) as BuildingTavern

    class BuildOrder(val postion : Vector2, val type : BuildingEnum)

    val toBuild : MutableList<BuildOrder> = mutableListOf()

    fun createVillage(){
        createStartingBuildings()
        createStartingPopulation()
    }

    fun createStartingBuildings(){

        val maxSize = Math.min(MapController.mapSize.x, MapController.mapSize.y) / 2
        for(i in 1..10) BuildingManager.buildBuilding(BuildingEnum.MINE, RandomUtils.getPointInCircle(maxSize))
        for(i in 1..1) BuildingManager.buildBuilding(BuildingEnum.FARM, RandomUtils.getPointInCircle(750.0))
        for(i in 1..2) BuildingManager.buildBuilding(BuildingEnum.HOUSE, RandomUtils.getPointInCircle(750.0))

        toBuild.add(BuildOrder(Vector2(100.0, 100.0), BuildingEnum.FARM))
    }

    fun createStartingPopulation(){
        for(i in 1..2) HeroesBuilder.placeHero(HeroesEnum.PEASANT, RandomUtils.getPointInCircle(100.0) )
    }

    override fun update() {

    }

    fun checkForNewBuildings(){
        
    }


    fun build() : BuildOrder?{
        var returnValue : BuildOrder? = null
        if(toBuild.isNotEmpty()) {
            returnValue = toBuild.find {
                ResourceManager.canAfford(BuildingCosts.costs.get(it.type)!!)
            }
            if(returnValue != null){
                ResourceManager.removeResources(BuildingCosts.costs.get(returnValue!!.type)!!)
                toBuild.remove(returnValue)
            }
        }

        return returnValue
    }

}