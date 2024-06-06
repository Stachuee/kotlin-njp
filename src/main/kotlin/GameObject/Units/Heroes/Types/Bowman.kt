package GameObject.Units.Heroes.Types

import GameObject.Units.Projectile
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Bowman (renderer: ObjectRenderer) : ThrowerBase(renderer) {

    override val range: Double = 750.0
    override val rateOfFire: Double = 2.0

    constructor() : this(ObjectRenderer("characters", Vector2(2.0,0.0), 0)) {
        this.setHP(25.0)
        this.setSpeed(100.0)
        damage = 15.0
    }

    override fun setProjectile(projectile: Projectile): Projectile {
        projectile.setType(Projectile.ProjectalType.ARROW)
            .setDamage(damage)
            .setExpolosive(false, 0.0, true)
            .setSpeed(500.0)
            .setWorldPosition(getWorldPosition())
        return projectile
    }
}