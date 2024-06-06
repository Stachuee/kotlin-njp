package GameObject.Units.Buildings.ProductionBuildings

import GameObject.Units.Buildings.BuildingBase
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.math.Vector2

class BuildingFarm (renderer: ObjectRenderer) : BuildingBase(renderer){

    var matureTime = 0.0
    var growing = false
    var readyToHarvest = false
    var locked = 0.0

    companion object{
        var yield = 3
        var growTime = 10.0
        var harvestLock = 20.0
        val allFarms : MutableList<BuildingFarm> = mutableListOf()

        fun findClosestFarm(postion : Vector2) : BuildingFarm?{
            var closest : BuildingFarm? = null
            var dist : Double = Double.POSITIVE_INFINITY

            allFarms.forEach{
                val currDist = it.getWorldPosition().distanceTo(postion)
                if(currDist < dist)
                {
                    closest = it
                    dist = currDist
                }
            }
            return closest
        }

        fun farmReadyToHarvest() : BuildingFarm?{
            var returnValue : BuildingFarm? = null
            allFarms.forEach{
                if(it.readyToHarvest && it.locked < Time.time) returnValue = it
            }
            returnValue?.harvestLock()
            return returnValue
        }

    }

    constructor() : this(ObjectRenderer("buildings", Vector2(1.0,2.0), 0)) {

        renderer.animator?.addAnimation("mature", AnimationLibrary.maturePlant)
        renderer.animator?.addAnimation("growing", AnimationLibrary.growPlant)
    }

    override fun update() {
        if(destroyed || building) return
        if(growing)
        {
            if(matureTime <= Time.time)
            {
                readyToHarvest = true
                growing = false
                renderer.animator?.triggerAnimation("mature")
            }
        }
    }

    override fun finishBuilding() {
        super.finishBuilding()
        allFarms.add(this)
        plant()
    }

    fun harvestLock(){
        locked = Time.time + harvestLock
    }


    fun plant(){
        matureTime = Time.time + growTime
        growing = true
        renderer.animator?.triggerAnimation("growing")
    }

    fun harvest() : Int{
        readyToHarvest = false
        growing = false
        plant()
        locked = 0.0
        return yield
    }
}