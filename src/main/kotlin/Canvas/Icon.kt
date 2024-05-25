package Canvas

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Icon : CanvasObject {

    constructor(renderer: ObjectRenderer, position: Vector2, size : Vector2) : super(renderer){
        canvasSize = size
        canvasPosition = position
    }

    override fun onClick() {

    }

    override fun onHoverEnter() {

    }

    override fun onHoverExit() {

    }

    override fun onHover() {

    }
}