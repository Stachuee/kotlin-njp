package GameObject.Units.Heroes

import GameObject.GameObject
import GameObject.Units.UnitBase
import GameObject.Units.UnitController
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

abstract class HeroBase: UnitBase {

    protected var state : HeroStates = HeroStates.IDLE
    protected var moveDirection : Vector2 = Vector2.ZERO
    protected var target : GameObject? = null

    constructor(renderer: ObjectRenderer) : super(renderer) {
        renderer.addAnimator()
        renderer.animator?.addAnimation("walk", AnimationLibrary.walk)
        renderer.animator?.addAnimation("idle", AnimationLibrary.idle)
        renderer.animator?.addAnimation("attack", AnimationLibrary.attack)

    }

    init {
        UnitController.addUnit(this)
    }

    override fun update() {
        renderer.sortingLayer = -getWorldPosition().y.toInt()
        renderer.setFlipped(moveDirection.x > 0)
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