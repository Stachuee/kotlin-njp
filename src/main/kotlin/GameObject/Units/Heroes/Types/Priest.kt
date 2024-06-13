package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroStats
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.UnitBase
import GameObject.Units.UnitController
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.math.Vector2

class Priest (renderer: ObjectRenderer) : WarriorBase(renderer) {

    constructor() : this(ObjectRenderer("characters", Vector2(4.0,1.0), 0)) {
        val hero = HeroStats.info.get(HeroesEnum.PRIEST)!!
        this.setMaxHp(hero.hp)
        this.setSpeed(hero.speed)
        damage = hero.damage
    }

    override fun findattackTarget() {
        var closest : UnitBase? = null
        var distance = Double.POSITIVE_INFINITY
        var downed : Boolean = false

        UnitController.unitArray.forEach {
            if(it.maxHp - 5 > it.hp || (downed && it.hp <= 0)){
                if(it.hp <= 0) downed = true;
                val newDist = it.getWorldPosition().squaredDistanceTo(getWorldPosition())
                if(distance > newDist){
                    closest = it
                    distance = newDist
                }
            }
        }
        attackTarget = closest

    }

    override fun attackattackTarget() {
        renderer.animator?.triggerAnimation("attack")
        if(attackTarget != null)
        {
            attackTarget!!.heal(damage * Time.deltaTime)
        }
    }

}