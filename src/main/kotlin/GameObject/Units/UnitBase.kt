package GameObject.Units

import GameObject.GameObject
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

open abstract class UnitBase : GameObject, ITakeDamage {

    var renderer : ObjectRenderer

    var hp = 0.0
    var maxHp = 0.0
    var speed = 0.0
    var targetable = true

    constructor(renderer : ObjectRenderer)
    {
        this.renderer = renderer
    }

    override fun setWorldPosition(position: Vector2)
    {
        super.setWorldPosition(position)
        this.renderer.setPosition(position)
        renderer.sortingLayer = -getWorldPosition().y.toInt()
    }

    fun setUnitPosition(position: Vector2) : UnitBase
    {
        setWorldPosition(position)
        return this
    }


    override fun takeDamage(damage: Double) {
        this.hp -= damage
    }


    fun setHP(hp: Double): UnitBase
    {
        this.hp = hp
        return this
    }

    fun setMaxHp(hp: Double): UnitBase
    {
        this.hp = hp
        this.maxHp = hp
        return this
    }


    fun heal(hp: Double): UnitBase
    {
        this.hp += hp
        return this
    }

    fun setSpeed(speed: Double): UnitBase{
        this.speed = speed
        return this
    }

    fun innerUnit()
    {
        renderer.setPosition(this.getWorldPosition())
        update()
    }

    abstract fun update()

    fun canBeTargeted() :Boolean{
        return targetable
    }

}