package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingTavern(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(0.0,1.0), 0)) {
        renderer.setPosition(getWorldPosition())
    }

    override fun update() {

    }
}