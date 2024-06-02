package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroBase
import GameObject.Units.Heroes.Types.Pesant.Companion.allPesants
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class SpearWarrior(renderer: ObjectRenderer) : WarriorBase(renderer) {

    constructor() : this(ObjectRenderer("characters", Vector2(1.0,0.0), 0)) {
        this.setHP(25.0)
        this.setSpeed(100.0)
    }

}