package GameObject.Units.Heroes

import GameObject.GameObject
import GameObject.Units.UnitBase
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

abstract class HeroBase(renderer: ObjectRenderer) : UnitBase(renderer) {

    protected var state : HeroStates = HeroStates.IDLE
    protected var moveDirection : Vector2 = Vector2.ZERO
    protected var target : GameObject? = null

    override fun update() {
        renderer.sortingLayer = -getWorldPosition().y.toInt()
        when (state){
            HeroStates.IDLE -> idle()
            HeroStates.PANICKED -> panic()
            HeroStates.WORK -> work()
        }
    }

    open fun idle(){

    }

    open fun panic(){

    }

    open fun work(){

    }
}