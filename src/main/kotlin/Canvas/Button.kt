package Canvas

import GameRenderer.ObjectRenderer
import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2

class Button : CanvasObject{

    enum class highlightType { TINT, SPRITE_CHANGE }


    constructor(renderer: ObjectRenderer, position: Vector2, size : Vector2) : super(renderer) {
        canvasSize = size
        canvasPosition = position
    }

    override fun onClick() {
    }

    override fun onHoverEnter() {
        renderer.getMaterial().setTint(ColorRGBa.GRAY)
    }

    override fun onHoverExit() {
        renderer.getMaterial().setTint(ColorRGBa.WHITE)
    }

    override fun onHover() {

    }


}