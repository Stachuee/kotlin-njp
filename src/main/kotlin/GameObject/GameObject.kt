package GameObject

import org.openrndr.math.Vector2

abstract class GameObject {
    private var worldPosition : Vector2 = Vector2(0.0, 0.0)
    private var active : Boolean = true

    open fun setWorldPosition(position :Vector2)
    {
        worldPosition = position
    }

    fun getWorldPosition() : Vector2 = worldPosition

    open fun setActive(active:Boolean){
        this.active = active
    }
    open fun getActive() : Boolean = active
}