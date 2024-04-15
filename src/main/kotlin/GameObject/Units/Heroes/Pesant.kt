package GameObject.Units.Heroes

import GameRenderer.ObjectRenderer
import GameObject.Units.UnitBase
import org.openrndr.math.Vector2

class Pesant(renderer: ObjectRenderer) : UnitBase(renderer){

    constructor() : this(ObjectRenderer("pesant", Vector2(0.0,0.0), 0)) {

    }

    override fun update() {

    }
}