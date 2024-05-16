package GameRenderer

import org.openrndr.math.Vector2

enum class RendererType{
    TEXT,
    IMAGE
}

class ObjectRenderer {


    var material : Material
    var sortingLayer : Int
    private var worldPosition : Vector2
    var rendererType : RendererType
    var rendererActive : Boolean = true

    var text : String = ""

    constructor(assetName : String, assetAtlasPosition: Vector2, sortingLayer: Int)
    {
        material = Material(assetAtlasPosition, assetName)
        this.sortingLayer = sortingLayer
        worldPosition = Vector2(0.0,0.0)
        rendererType = RendererType.IMAGE
        GameCamera.addRenderer(this)
    }

    constructor(material : Material, sortingLayer: Int)
    {
        this.material = material
        this.sortingLayer = sortingLayer
        rendererType = RendererType.IMAGE
        worldPosition = Vector2(0.0,0.0)
    }

    constructor(material : Material, sortingLayer: Int, text : String)
    {
        this.material = material
        this.sortingLayer = sortingLayer
        rendererType = RendererType.TEXT
        this.text = text
        worldPosition = Vector2(0.0,0.0)
    }


    fun getPostion() : Vector2{
        return worldPosition
    }

    fun setPosition(newPosition : Vector2){
        worldPosition = newPosition
    }


    fun removeRenderer()
    {
        GameCamera.removeRenderer(this)
    }



}