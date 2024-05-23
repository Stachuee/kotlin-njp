package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingMine(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(2.0,0.0), 0)) {
        renderer.setPosition(getWorldPosition())
    }

    override fun update() {

    }
}