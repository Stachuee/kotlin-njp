package GameObject.Units.Buildings

import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingTower(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(1.0,1.0), 0)){
        renderer.animator!!.addAnimation("build", AnimationLibrary.buildingTower)
        allTowers.add(this)
    }
    companion object{
        val allTowers : MutableList<BuildingTower> = mutableListOf()
    }

    override fun update() {

    }
}