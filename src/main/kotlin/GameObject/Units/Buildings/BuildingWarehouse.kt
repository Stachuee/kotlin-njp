package GameObject.Units.Buildings

import GameManagers.ResourceManager
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingWarehouse(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(0.0,1.0), 0)){
        renderer.animator!!.addAnimation("build", AnimationLibrary.buildingWarehouse)
        HeroesBuilder.placeHero(HeroesEnum.WARRIOR, getWorldPosition())
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

    override fun finishBuilding() {
        super.finishBuilding()
        allWarehouses.add(this)
    }

    fun deposit(gold : Int, food : Int)
    {
        ResourceManager.addResources(gold, food)
    }
}