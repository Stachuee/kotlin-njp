package GameObject.Units.Buildings

import GameManagers.ResourceManager
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingTavern(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(0.0,1.0), 0))

    override fun update() {

    }

    fun deposit(gold : Int, food : Int)
    {
        ResourceManager.addResources(gold, food)
    }
}