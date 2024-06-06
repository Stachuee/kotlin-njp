package GameObject.Units.Heroes.Types

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Prisoner(renderer: ObjectRenderer) : WarriorBase(renderer) {

    constructor() : this(ObjectRenderer("characters", Vector2(1.0,0.0), 0)) {
        this.setHP(25.0)
        this.setSpeed(100.0)
        damage = 15.0
    }

}