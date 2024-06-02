package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingTower(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(1.0,1.0), 0)){
        allHouses.add(this)
    }

    companion object{
        val allHouses : MutableList<BuildingTower> = mutableListOf()
    }

    override fun update() {

    }
}