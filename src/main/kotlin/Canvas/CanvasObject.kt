package Canvas

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

abstract class CanvasObject {
    var canvasPosition : Vector2 = Vector2(0.0, 0.0)
    var canvasSize : Vector2 = Vector2(0.0, 0.0)

    var raycastTarget : Boolean = true
    var renderer : ObjectRenderer

    constructor(renderer : ObjectRenderer) {
        this.renderer = renderer
    }



    abstract fun onClick()
    abstract fun onHoverEnter()
    abstract fun onHoverExit()
    abstract fun onHover()

}