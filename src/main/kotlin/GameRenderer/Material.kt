package GameRenderer

import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2

class Material {

    private val atlasPosition : Vector2
    private var atlasName : String
    private var tint : ColorRGBa
    var atlasUnitSize = 160.0


    constructor(atlasPosition : Vector2, atlasName : String)
    {
        this.atlasPosition = atlasPosition * atlasUnitSize
        tint = ColorRGBa.WHITE
        this.atlasName = atlasName
    }

    constructor(atlasPosition : Vector2, atlasName : String, atlasSize : Vector2)
    {
        this.atlasPosition = atlasPosition * atlasSize
        tint = ColorRGBa.WHITE
        this.atlasName = atlasName
    }

    fun getAtlasPosition () : Vector2 = atlasPosition
    fun getAtlasName () : String = atlasName
    fun getTint () : ColorRGBa = tint

    fun setTint (tint : ColorRGBa) {
        this.tint = tint
    }

}