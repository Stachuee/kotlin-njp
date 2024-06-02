package GameObject.Units.Buildings

import GameManagers.ResourceManager
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingWarehouse(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(0.0,1.0), 0)){
        allWarehouses.add(this)
    }

    override fun update() {

    }

    companion object{
        val allWarehouses : MutableList<BuildingWarehouse> = mutableListOf()

        fun findClosestWarehouse(postion : Vector2) : BuildingWarehouse?{
            var closest : BuildingWarehouse? = null
            var dist : Double = Double.POSITIVE_INFINITY

            allWarehouses.forEach{
                val currDist = it.getWorldPosition().distanceTo(postion)
                if(currDist < dist)
                {
                    closest = it
                    dist = currDist
                }
            }
            return closest
        }
    }

    fun deposit(gold : Int, food : Int)
    {
        ResourceManager.addResources(gold, food)
    }
}