package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroStats
import GameObject.Units.Heroes.HeroesEnum
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Knight (renderer: ObjectRenderer) : WarriorBase(renderer) {

    constructor() : this(ObjectRenderer("characters", Vector2(1.0,2.0), 0)) {
        val hero = HeroStats.info.get(HeroesEnum.KNIGHT)!!
        this.setMaxHp(hero.hp)
        this.setSpeed(hero.speed)
        damage = hero.damage
    }

}