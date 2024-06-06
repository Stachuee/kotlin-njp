package GameObject.Units.Heroes.Types

import GameObject.Units.Projectile
import GameObject.Units.ProjectileBuilder
import GameRenderer.ObjectRenderer
import SimulationEngine.Time

abstract class ThrowerBase (renderer: ObjectRenderer) : WarriorBase(renderer) {

    protected abstract val range : Double
    protected abstract val rateOfFire : Double

    var nextShot = 0.0

    override fun work() {
        target = null
        if(attackTarget != null && attackTarget!!.getWorldPosition().distanceTo(getWorldPosition()) < range)
        {
            renderer.animator?.triggerAnimation("idle")
            attackattackTarget()
        }
        else
        {
            renderer.animator?.triggerAnimation("walk")
            followattackTarget()
        }
    }

    override fun attackattackTarget() {
        if(attackTarget == null) return

        if(nextShot <= Time.time){
            val projectile = setProjectile(ProjectileBuilder.getProjectile())
            projectile.setTarget(attackTarget!!).activate()
            nextShot = Time.time + rateOfFire
        }

    }

    abstract fun setProjectile(projectile: Projectile) : Projectile

}

