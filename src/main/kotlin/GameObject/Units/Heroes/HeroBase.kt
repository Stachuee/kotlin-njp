package GameObject.Units.Heroes

import GameObject.GameObject
import GameObject.Units.UnitBase
import GameObject.Units.UnitController
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.math.Vector2

abstract class HeroBase: UnitBase {

    var state : HeroStates = HeroStates.IDLE
    protected var moveDirection : Vector2 = Vector2.ZERO
    protected var target : GameObject? = null

    protected var down = false

    constructor(renderer: ObjectRenderer) : super(renderer) {
        renderer.addAnimator()
        renderer.animator?.addAnimation("walk", AnimationLibrary.walk)
        renderer.animator?.addAnimation("idle", AnimationLibrary.idle)
        renderer.animator?.addAnimation("attack", AnimationLibrary.attack)
        renderer.animator?.addAnimation("down", AnimationLibrary.down)
        UnitController.addUnit(this)
    }


    override fun update() {
        renderer.sortingLayer = -getWorldPosition().y.toInt() - 100
        if(down) return
        renderer.setFlipped(moveDirection.x > 0)
        regenHp()
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

    override fun takeDamage(damage: Double) {
        if(down) return
        super.takeDamage(damage)
        if(hp <= 0)
        {
            renderer.animator!!.triggerAnimation("down")
            down = true
            targetable = false
        }
    }

    fun regenHp(){
        setHP(hp + 1 * Time.deltaTime)
    }

}