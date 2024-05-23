package GameObject.Units

import GameObject.GameObject
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

open abstract class UnitBase : GameObject {

    var renderer : ObjectRenderer

    var hp = 0.0
    var speed = 0.0

    constructor(renderer : ObjectRenderer)
    {
        this.renderer = renderer
        renderer.sortingLayer = -getWorldPosition().y.toInt()
    }


    override fun setWorldPosition(position: Vector2)
    {
        super.setWorldPosition(position)
        this.renderer.setPosition(position)
    }

    fun setUnitPosition(position: Vector2) : UnitBase
    {
        setWorldPosition(position)
        return this
    }





    fun setHP(hp: Double): UnitBase
    {
        this.hp = hp
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

}