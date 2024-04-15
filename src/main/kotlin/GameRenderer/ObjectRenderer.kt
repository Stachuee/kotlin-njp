package GameRenderer

import org.openrndr.math.Vector2

class ObjectRenderer {

    private var material : Material
    private var sortingLayer : Int
    private var worldPosition : Vector2


    constructor(assetName : String, assetAtlasPosition: Vector2, sortingLayer: Int)
    {
        material = Material(assetAtlasPosition, assetName)
        this.sortingLayer = sortingLayer
        worldPosition = Vector2(0.0,0.0)
        GameCamera.getInstance().addRenderer(this)
    }

    constructor(material : Material, sortingLayer: Int)
    {
        this.material = material
        this.sortingLayer = sortingLayer
        worldPosition = Vector2(0.0,0.0)
    }

    fun getPostion() : Vector2{
        return worldPosition
    }

    fun setPosition(newPosition : Vector2){
        worldPosition = newPosition
    }

    fun getSortingLayer() : Int
    {
        return sortingLayer
    }

    fun getMaterial() : Material{
        return material
    }


}