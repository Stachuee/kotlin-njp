package GameManagers

import GameObject.Units.Buildings.*
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.Heroes.Types.Pesant
import SimulationEngine.Time
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

object VillageController : IUpdate {

    val checkTimer = Vector2(10.0, 30.0)
    var developmentCooldown = 5.0
    var vilageRange = 500.0

    class BuildOrder(val postion : Vector2, val type : BuildingEnum)

    val toBuild : MutableList<BuildOrder> = mutableListOf()

    fun createVillage(){
        createStartingBuildings()
        createStartingPopulation()
    }

    fun createStartingBuildings(){

        val maxSize = Math.min(MapController.mapSize.x, MapController.mapSize.y) / 2
        for(i in 1..10) BuildingManager.buildBuilding(BuildingEnum.MINE, RandomUtils.getPointInCircle(maxSize))

        toBuild.add(BuildOrder(RandomUtils.getPointInCircle(vilageRange), BuildingEnum.WAREHOUSE))
        toBuild.add(BuildOrder(RandomUtils.getPointInCircle(vilageRange), BuildingEnum.FARM))
    }

    fun createStartingPopulation(){
        for(i in 1..1) HeroesBuilder.placeHero(HeroesEnum.PEASANT, RandomUtils.getPointInCircle(100.0) )
    }

    override fun update() {
        develop()
    }

    fun checkForNewBuildings(){
        
    }

    fun develop(){

        developmentCooldown = developmentCooldown - Time.deltaTime
        if(developmentCooldown <= 0)
        {
            if(BuildingHouse.allHouses.size >= Pesant.allPesants.size)
            {
                HeroesBuilder.placeHero(HeroesEnum.PEASANT, BuildingHouse.allHouses[Random.int(0, BuildingHouse.allHouses.size)].getWorldPosition() )
            }
            else if(toBuild.find { it.type == BuildingEnum.HOUSE } == null)
            {
                toBuild.add(BuildOrder(RandomUtils.getPointInCircle(vilageRange), BuildingEnum.HOUSE))
            }
            developmentCooldown = Random.double(checkTimer.x, checkTimer.y)
        }
    }

    fun build() : BuildOrder?{
        var returnValue : BuildOrder? = null
        if(toBuild.isNotEmpty()) {
            returnValue = toBuild.find {
                ResourceManager.canAfford(BuildingCosts.costs.get(it.type)!!)
            }
            if(returnValue != null){
                ResourceManager.removeResources(BuildingCosts.costs.get(returnValue.type)!!)
                toBuild.remove(returnValue)
            }
        }

        return returnValue
    }

}