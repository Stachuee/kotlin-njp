package GameObject.Units.Foliage

import GameObject.GameObject
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Foliage(var renderer: ObjectRenderer) : GameObject() {


    init {
        GameCamera.addRenderer(renderer)
    }

    override fun setWorldPosition(position: Vector2) {
        super.setWorldPosition(position)
        renderer.setPosition(position)
        renderer.sortingLayer = -getWorldPosition().y.toInt()
    }

    override fun setActive(active: Boolean) {
        super.setActive(active)
        renderer.rendererActive = false
    }
}