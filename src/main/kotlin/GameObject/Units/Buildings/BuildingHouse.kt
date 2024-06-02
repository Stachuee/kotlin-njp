package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingHouse(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(0.0,0.0), 0)){
        allHouses.add(this)
    }

    companion object{
        val allHouses : MutableList<BuildingHouse> = mutableListOf()
    }

    override fun update() {

    }
}