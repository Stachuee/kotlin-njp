package GameObject.Units

import GameObject.GameObject
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import Utils.MathUtils
import org.openrndr.math.Vector2

class Projectile: GameObject {
    var renderer : ObjectRenderer

    enum class ProjectalType{
        ARROW,
        REDBALL,
        WHITEBALL
    }

    var target: GameObject? = null
    var damage : Double = 0.0
    var speed : Double = 0.0
    var expolosive : Boolean = false
    var explosionRange : Double = 0.0
    var allyExplosive :Boolean = true


    var finished: Boolean = false

    constructor()
    {
        this.renderer = ObjectRenderer("UIOne", Vector2(4.0, 1.0), 0)

    }

    fun setTarget(gameObject: GameObject) : Projectile {
        target = gameObject
        return this
    }

    fun setDamage(damage: Double) : Projectile{
        this.damage = damage
        return this
    }

    fun setSpeed(speed : Double): Projectile{
        this.speed = speed
        return this
    }

    fun setExpolosive(expolosive: Boolean, range : Double, allyExplosive: Boolean) : Projectile{
        this.expolosive = expolosive
        this.explosionRange = range
        this.allyExplosive = allyExplosive
        return this
    }

    override fun setWorldPosition(position: Vector2) {
        super.setWorldPosition(position)
        renderer.setPosition(getWorldPosition())
    }

    fun activate(): Projectile{
        finished = false
        setActive(true)
        renderer.rendererActive = true
        UnitController.addProjectile(this)
        return this
    }

    fun setType(type : ProjectalType): Projectile{
        when(type)
        {
            ProjectalType.ARROW -> renderer.material.setSprite("UIOne", Vector2(4.0, 1.0))
            ProjectalType.REDBALL -> renderer.material.setSprite("UIOne", Vector2(3.0, 2.0))
            ProjectalType.WHITEBALL -> renderer.material.setSprite("UIOne", Vector2(4.0, 2.0))
        }
        return this
    }

    fun update(){
        if(target == null || finished) return

        if(!target!!.getActive()){
            finished = true
            setActive(false)
            renderer.rendererActive = false
            UnitController.removeProjectile(this)
        }

        val moveVector = (target!!.getWorldPosition() - getWorldPosition())

        if(moveVector.length < 5) {
            hit()
        }
        else {
            setWorldPosition(getWorldPosition() + moveVector.normalized * speed * Time.deltaTime)
            renderer.setRotation(MathUtils.rotateTo(getWorldPosition(), target!!.getWorldPosition()) + 90)
        }
    }

    fun hit()
    {
        if(target != null){
            if(target is ITakeDamage)
            {
                (target as ITakeDamage).takeDamage(damage)
            }
            if(expolosive){
                if(allyExplosive)
                {
                    UnitController.enemyArray.forEach {
                        if(it.getWorldPosition().distanceTo(getWorldPosition()) < explosionRange)
                            it.takeDamage(damage)
                    }
                }
                else{
                    UnitController.unitArray.forEach {
                        if(it.getWorldPosition().distanceTo(getWorldPosition()) < explosionRange)
                            it.takeDamage(damage)
                    }
                }
            }
        }
        finished = true
        setActive(false)
        renderer.rendererActive = false
        UnitController.removeProjectile(this)
    }

}