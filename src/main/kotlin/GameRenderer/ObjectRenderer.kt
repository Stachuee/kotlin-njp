package GameRenderer

import GameRenderer.Animations.Animation
import org.openrndr.extra.gui.addTo
import org.openrndr.math.Vector2

enum class RendererType{
    TEXT,
    IMAGE
}

class ObjectRenderer {

    class Animator {
        var animationLibrary : MutableMap<String, Animation> = mutableMapOf()
        var currentAnimation : Animation? = null

        fun addAnimation(trigger: String, animation: Animation) {
            animationLibrary.put(trigger, animation)
        }

        fun triggerAnimation(trigger : String)
        {
            currentAnimation = animationLibrary[trigger]
            if(currentAnimation != null)
            {
                currentAnimation!!.startAnimation()
            }
        }

        fun updateAnimation(renderer: ObjectRenderer){
            currentAnimation?.playAnimation(renderer)
        }
    }

    var material : Material
    var sortingLayer : Int

    private var worldPosition : Vector2 = Vector2(0.0,0.0)
    private var rotation : Double = 0.0
    private var scale : Vector2 = Vector2(1.0,1.0)
    private var flipped : Boolean = false

    var rendererType : RendererType
    var rendererActive : Boolean = true

    var animator : Animator? = null

    var text : String = ""

    constructor(assetName : String, assetAtlasPosition: Vector2, sortingLayer: Int)
    {
        material = Material(assetAtlasPosition, assetName)
        this.sortingLayer = sortingLayer
        rendererType = RendererType.IMAGE
        GameCamera.addRenderer(this)
    }

    constructor(material : Material, sortingLayer: Int)
    {
        this.material = material
        this.sortingLayer = sortingLayer
        rendererType = RendererType.IMAGE
    }

    constructor(material : Material, sortingLayer: Int, text : String)
    {
        this.material = material
        this.sortingLayer = sortingLayer
        rendererType = RendererType.TEXT
        this.text = text
    }


    fun getPostion() : Vector2{
        return worldPosition
    }

    fun getRotation() : Double{
        return rotation
    }

    fun getScale() : Vector2{
        return Vector2(scale.x * (if(flipped) -1 else 1), scale.y)
    }

    fun getFlipped() : Boolean{
        return flipped
    }

    fun setPosition(newPosition : Vector2){
        worldPosition = newPosition
    }

    fun setRotation(newRotation: Double){
        rotation = newRotation
    }

    fun setScale(newScale : Vector2){
        scale = newScale
    }

    fun setFlipped(newFlipped : Boolean){
        flipped = newFlipped
    }

    fun removeRenderer()
    {
        GameCamera.removeRenderer(this)
    }

    fun addAnimator(){
        animator = Animator()
    }


}