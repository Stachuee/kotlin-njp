package GameObject.Units.Enemies.Mele

import GameObject.Units.Enemies.EnemyBase
import GameObject.Units.Enemies.EnemyEnum
import GameObject.Units.Enemies.EnemyStats
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Zombie (renderer: ObjectRenderer) : EnemyBase(renderer) {

    constructor() : this(ObjectRenderer("enemies", Vector2(0.0,1.0), 0)) {

    }

    override fun setStats() {
        enemyType = EnemyEnum.ZOMBIE
        val temp = EnemyStats.info.get(enemyType)!!
        this.setHP(temp.hp)
        this.setSpeed(temp.speed)
        damage = temp.damage
    }
}