package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroBase
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Warrior(renderer: ObjectRenderer) : HeroBase(renderer){

    constructor() : this(ObjectRenderer("characters", Vector2(0.0,0.0), 0)) {

    }

    override fun update() {

    }
}