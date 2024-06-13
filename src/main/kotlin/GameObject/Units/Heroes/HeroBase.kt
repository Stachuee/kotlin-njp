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
        renderer.animator?.addAnimation("walk", AnimationLibrary.walk.copy())
        renderer.animator?.addAnimation("idle", AnimationLibrary.idle.copy())
        renderer.animator?.addAnimation("attack", AnimationLibrary.attack.copy())
        renderer.animator?.addAnimation("down", AnimationLibrary.down.copy())
        UnitController.addUnit(this)
    }


    override fun update() {
        renderer.sortingLayer = -getWorldPosition().y.toInt() - 100
        regenHp()
        if(down) {
            recover()
            return
        }
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

    fun recover(){
        if(hp >= maxHp - 1)
        {
            down = false
            targetable = true
        }
    }

    fun regenHp(){
        var multiplier = 1.0
        if (down) multiplier = 5.0
        setHP(Math.min(hp + 1 * Time.deltaTime * multiplier, maxHp))
    }

}