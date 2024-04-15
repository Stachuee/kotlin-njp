package GameObject.Units

import GameObject.GameObject
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

open abstract class UnitBase : GameObject {

    var renderer : ObjectRenderer

    var active = false
    var hp = 0.0

    constructor(renderer : ObjectRenderer)
    {
        this.renderer = renderer
    }


    fun setUnitPosition(position: Vector2) : UnitBase
    {
        this.worldPosition = position
        this.renderer.setPosition(position)
        return this
    }

    fun setActive(active: Boolean): UnitBase
    {
        this.active = active
        return this
    }

    fun setHP(hp: Double): UnitBase
    {
        this.hp = hp
        return this
    }

    fun innerUnit()
    {
        renderer.setPosition(this.worldPosition)
        update()
    }

    abstract fun update()

}