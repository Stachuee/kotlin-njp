package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroStats
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.Projectile
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Bowman (renderer: ObjectRenderer) : ThrowerBase(renderer) {

    override val range: Double = 750.0
    override var rateOfFire: Double = 2.0

    constructor() : this(ObjectRenderer("characters", Vector2(2.0,0.0), 0)) {
        val hero = HeroStats.info.get(HeroesEnum.BOWMAN)!!
        this.setMaxHp(hero.hp)
        this.setSpeed(hero.speed)
        this.rateOfFire = hero.attackDelay
        damage = hero.damage
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