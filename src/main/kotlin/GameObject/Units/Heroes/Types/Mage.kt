package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroStats
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.Projectile
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Mage (renderer: ObjectRenderer) : ThrowerBase(renderer) {

    override val range: Double = 1000.0
    override var rateOfFire: Double = 3.0

    constructor() : this(ObjectRenderer("characters", Vector2(3.0,1.0), 0)) {
        val hero = HeroStats.info.get(HeroesEnum.MAGE)!!
        this.setMaxHp(hero.hp)
        this.setSpeed(hero.speed)
        this.rateOfFire = hero.attackDelay
        damage = hero.damage
    }

    override fun setProjectile(projectile: Projectile): Projectile {
        projectile.setType(Projectile.ProjectalType.REDBALL)
            .setDamage(damage)
            .setExpolosive(true, 200.0, true)
            .setSpeed(400.0)
            .setWorldPosition(getWorldPosition())
        return projectile
    }
}