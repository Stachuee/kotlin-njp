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
        renderer.setPosition(this.getWorldPosition())
        update()
    }

    abstract fun update()

}