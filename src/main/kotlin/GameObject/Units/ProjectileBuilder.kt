package GameObject.Units

import org.openrndr.math.Vector2

object ProjectileBuilder {
    var projectilePool : MutableList<Projectile> = mutableListOf<Projectile>()

    fun returnProjectile(projectile: Projectile){
        projectilePool.add(projectile)
    }

    fun getProjectile() : Projectile {
        if(projectilePool.size > 0)
        {
            val toReturn = projectilePool[0]
            projectilePool.removeAt(0)
            return toReturn
        }
        else{
            return Projectile()
        }
    }
}