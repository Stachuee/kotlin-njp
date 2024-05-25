package GameRenderer

import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2
import org.openrndr.panel.style.Position

class Material {

    private var atlasPosition : Vector2
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

    fun setSprite(atlas: String, position: Vector2, atlasSize : Double = atlasUnitSize){
        atlasName = atlas
        atlasPosition = position * atlasSize
    }

}