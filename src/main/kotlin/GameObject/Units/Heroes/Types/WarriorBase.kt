package GameObject.Units.Heroes.Types

import GameObject.Units.Heroes.HeroBase
import GameObject.Units.Heroes.HeroStates
import GameObject.Units.UnitBase
import GameObject.Units.UnitController
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.extra.noise.Random

abstract class WarriorBase(renderer: ObjectRenderer) : HeroBase(renderer){
    
    var attackTarget : UnitBase? = null


    var nextCheck = Random.double0(1.0) + Time.time

    var damage = 0.0

    var patrol = 0.0

    companion object{
        var attackTargetCheck = 1.0
        var patrolTimer = 3.0
    }

    override fun update() {
        super.update()
        if(nextCheck < Time.time)
        {
            findattackTarget()
            nextCheck += attackTargetCheck
        }
        if(attackTarget == null) state = HeroStates.IDLE
        else state = HeroStates.WORK

    }

    fun findattackTarget(){
        var closest : UnitBase? = null
        var distance = Double.POSITIVE_INFINITY

        UnitController.enemyArray.forEach {
            if(it.canBeTargeted()){
                val newDist = it.getWorldPosition().squaredDistanceTo(getWorldPosition())
                if(distance > newDist){
                    closest = it
                    distance = newDist
                }
            }
        }
        attackTarget = closest


    }


    fun followattackTarget(){
        renderer.animator?.triggerAnimation("walk")
        if(attackTarget != null)
        {
            val moveDirection = (attackTarget!!.getWorldPosition() - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }

    fun attackattackTarget(){
        renderer.animator?.triggerAnimation("attack")
        if(attackTarget != null)
        {
            attackTarget!!.takeDamage(damage * Time.deltaTime)
        }
    }

    override fun idle() {
        super.idle()
        if(target != null && patrol > Time.time)
        {
            renderer.animator!!.triggerAnimation("idle")
            return
        }

        renderer.animator!!.triggerAnimation("walk")

        if(target == null)
            target = UnitController.unitArray.random()

        val moveDirection = (target!!.getWorldPosition() - getWorldPosition()).normalized
        setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)

        if(target!!.getWorldPosition().distanceTo(getWorldPosition()) < 150) {
            target = null
            patrol = patrolTimer + Time.time
        }
    }

    override fun work() {
        super.work()
        target = null
        if(attackTarget != null && attackTarget!!.getWorldPosition().distanceTo(getWorldPosition()) < 50)
        {
            attackattackTarget()
        }
        else
        {
            followattackTarget()
        }
    }
}