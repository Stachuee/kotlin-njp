package GameObject.Units.Foliage

import GameObject.GameObject
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Foliage(var renderer: ObjectRenderer) : GameObject() {

    var active = true

    init {
        GameCamera.addRenderer(renderer)
    }

    override fun setWorldPosition(position: Vector2) {
        super.setWorldPosition(position)
        renderer.setPosition(position)
    }
}