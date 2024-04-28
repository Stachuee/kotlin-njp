package GameObject

import org.openrndr.math.Vector2

abstract class GameObject {
    private var worldPosition : Vector2 = Vector2(0.0, 0.0)

    open fun setWorldPosition(position :Vector2)
    {
        worldPosition = position
    }

    fun getWorldPosition() : Vector2 = worldPosition
}