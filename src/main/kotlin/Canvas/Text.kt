package Canvas

import GameRenderer.ObjectRenderer
import GameRenderer.RendererType
import org.openrndr.math.Vector2

class Text : CanvasObject {

    constructor(renderer: ObjectRenderer, position: Vector2, size : Vector2) : super(renderer) {
        canvasSize = size
        canvasPosition = position
        renderer.rendererType = RendererType.TEXT
    }

    fun setText(text: String) {
        renderer.text = text
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