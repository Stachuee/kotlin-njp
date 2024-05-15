package GameObject.Units.Heroes

import GameObject.Units.UnitBase
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

abstract class HeroBase(renderer: ObjectRenderer) : UnitBase(renderer) {

    protected val state : HeroStates = HeroStates.IDLE
    protected var moveDirection : Vector2 = Vector2.ZERO

    override fun update() {
        renderer.sortingLayer = -getWorldPosition().y.toInt()
        when (state){
            HeroStates.IDLE -> idle()
            HeroStates.PANICKED -> panic()
        }
    }

    open fun idle(){

    }

    open fun panic(){

    }
}