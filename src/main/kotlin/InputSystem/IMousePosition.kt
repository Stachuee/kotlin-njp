package InputSystem

import org.openrndr.math.Vector2

interface IMousePosition {
    fun mousePositionChanged(newPosition : Vector2)
}