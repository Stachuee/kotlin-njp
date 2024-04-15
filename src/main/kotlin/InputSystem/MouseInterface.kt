package InputSystem

import org.openrndr.MouseButton
import org.openrndr.math.Vector2

interface MouseInterface {
    fun mouseDown(event : MouseButton)
    fun mouseUp(event : MouseButton)
}