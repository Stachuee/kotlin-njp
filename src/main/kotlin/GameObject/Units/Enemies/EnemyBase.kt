package GameObject.Units.Enemies

import GameObject.Units.ITakeDamage
import GameObject.Units.UnitBase
import GameObject.Units.UnitController
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

abstract class EnemyBase : UnitBase {

    var target : UnitBase? = null

    abstract var enemyType : EnemyEnum

    var nextCheck = Random.double0(1.0) + Time.time

    var damage = 0.0

    companion object{
        var targetCheck = 1.0
    }

    constructor(renderer: ObjectRenderer) : super(renderer) {
        renderer.addAnimator()
        renderer.animator?.addAnimation("walk", AnimationLibrary.walk)
        renderer.animator?.addAnimation("idle", AnimationLibrary.idle)
        renderer.animator?.addAnimation("attack", AnimationLibrary.attack)
        setStats()
    }

    init {
        UnitController.addEnemy(this)
    }

    override fun update() {
        if(!getActive()) return
        if(nextCheck < Time.time)
        {
            findTarget()
            nextCheck += targetCheck
        }
        if(target != null && target!!.getWorldPosition().distanceTo(getWorldPosition()) < 50)
        {
            attackTarget()
        }
        else
        {
            followTarget()
        }
    }

    fun findTarget(){
        var closest : UnitBase? = null
        var distance = Double.POSITIVE_INFINITY

        UnitController.unitArray.forEach {
            if(it.canBeTargeted()){
                val newDist = it.getWorldPosition().squaredDistanceTo(getWorldPosition())
                if(distance > newDist){
                    closest = it
                    distance = newDist
                }
            }
        }
        if(closest != null ){
            target = closest
        }
    }


    fun followTarget(){
        renderer.animator?.triggerAnimation("walk")
        if(target != null)
        {
            val moveDirection = (target!!.getWorldPosition() - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }

    fun attackTarget(){
        renderer.animator?.triggerAnimation("attack")
        if(target != null)
        {
            target!!.takeDamage(damage * Time.deltaTime)
        }
    }

    override fun takeDamage(damage: Double) {
        if(!getActive()) return
        super.takeDamage(damage)
        if(hp <= 0){
            targetable = false
            setActive(false)
            renderer.rendererActive = false
            EnemyBuilder.returnEnemy(this)
        }
    }

    abstract fun setStats()

    fun reset(position: Vector2) {
        setWorldPosition(position)
        targetable = true
        renderer.rendererActive = true
        setActive(true)
        setStats()
    }
}