package GameObject.Units.Enemies

import GameObject.Units.Heroes.Types.Pesant.Companion.allPesants
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Goblin(renderer: ObjectRenderer) : EnemyBase(renderer) {

    constructor() : this(ObjectRenderer("enemies", Vector2(0.0,0.0), 0)) {
        this.setHP(10.0)
        this.setSpeed(75.0)
        damage = 10.0
    }

}