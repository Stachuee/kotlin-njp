package InputSystem

import org.openrndr.MouseButton

interface IMouseButton {
    fun mouseDown(event : MouseButton)
    fun mouseUp(event : MouseButton)
}
