package GameManagers

import GameObject.Units.Buildings.*
import SimulationEngine.Time
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

object VillageController : IUpdate {

    val checkTimer = Vector2(10.0, 30.0)
    var developmentCooldown = 5.0
    var vilageRange = 500.0

    class BuildOrder(val postion : Vector2, val type : BuildingEnum)

    private val toBuild : MutableList<BuildingBase> = mutableListOf()
    private val toRepair : MutableList<BuildingBase> = mutableListOf()

    fun createVillage(){
        createStartingBuildings()
    }

    fun createStartingBuildings(){

        val maxSize = Math.min(MapController.mapSize.x, MapController.mapSize.y) / 2

        BuildingFactory.buildBuilding(BuildingEnum.HOUSE, RandomUtils.getPointInCircle(vilageRange)).finishBuilding()

        toBuild.add(BuildingFactory.buildBuilding(BuildingEnum.WAREHOUSE, BuildingManager.findBuildingSpot()))
        toBuild.add(BuildingFactory.buildBuilding(BuildingEnum.FARM, BuildingManager.findBuildingSpot()))
        toBuild.add(BuildingFactory.buildBuilding(BuildingEnum.MINE, BuildingManager.findBuildingSpot()))
    }


    override fun update() {
        develop()
    }

    fun checkForNewBuildings(){
        
    }

    fun addToRepair(toAdd : BuildingBase){
        toRepair.add(toAdd)
    }

    fun develop(){

        developmentCooldown = developmentCooldown - Time.deltaTime
        if(developmentCooldown <= 0)
        {
            val canAfford = BuildingInfo.info.filter { ResourceManager.canAfford(it.value.cost) }
            var highest = Double.NEGATIVE_INFINITY
            var building = BuildingEnum.HOUSE

            canAfford.forEach{
                val value = it.value.weight + (BuildingManager.getBuildingCount(it.key) * it.value.weightChange)
                if(value > highest)
                {
                    highest = value
                    building = it.key
                }
            }

            if(highest > -100000.0)
            {
                toBuild.add(BuildingFactory.buildBuilding(building, BuildingManager.findBuildingSpot()))
                ResourceManager.removeResources(BuildingInfo.info.get(building)!!.cost)
            }

            developmentCooldown = Random.double(checkTimer.x, checkTimer.y)
        }
    }

    fun build() : BuildingBase?{
        var returnValue : BuildingBase? = null
        if(toRepair.isNotEmpty()){
            returnValue = toRepair[0]
            toRepair.removeFirst()
            return returnValue
        }

        if(toBuild.isNotEmpty()) {
            returnValue = toBuild[0]
            toBuild.removeFirst()
        }
        return returnValue
    }

    fun expandVillage() {
        vilageRange += 100.0
    }

}