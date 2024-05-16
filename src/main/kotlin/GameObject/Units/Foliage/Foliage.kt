package GameObject.Units.Foliage

import GameObject.GameObject
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class Foliage(var renderer: ObjectRenderer) : GameObject() {

    var chopsRemaining = 1


    init {
        GameCamera.addRenderer(renderer)
    }

    fun chop(){
        chopsRemaining -= 1
        if(chopsRemaining <= 0){
            setActive(false)
        }
    }

    override fun setWorldPosition(position: Vector2) {
        super.setWorldPosition(position)
        renderer.setPosition(position)
    }

    override fun setActive(active: Boolean) {
        super.setActive(active)
        renderer.rendererActive = false
    }
}