package GameObject.Units.Buildings

import GameObject.Units.Buildings.BuildingFarm.Companion.allFarms
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingMine(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(2.0,0.0), 0)){
        renderer.animator!!.addAnimation("build", AnimationLibrary.buildingMine)
    }

    companion object{
        val yield = 3
        val allMines : MutableList<BuildingMine> = mutableListOf()

        fun findClosestMine(postion : Vector2) : BuildingMine?{
            var closest : BuildingMine? = null
            var dist : Double = Double.POSITIVE_INFINITY

            allMines.forEach{
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

    fun harvest() = yield

    override fun update() {

    }

    override fun finishBuilding() {
        super.finishBuilding()
        allMines.add(this)
    }
}