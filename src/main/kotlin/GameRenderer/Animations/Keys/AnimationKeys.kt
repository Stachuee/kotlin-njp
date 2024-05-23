package GameRenderer.Animations.Keys

import GameRenderer.ObjectRenderer

abstract class AnimationKeys {
    enum class Interpolation{
        LINEAR
    }
    var interpolation : Interpolation = Interpolation.LINEAR

    fun evaluate(renderer: ObjectRenderer, timeStamp : Double){
        when(interpolation){
            Interpolation.LINEAR -> linearInterpolation(renderer, timeStamp)
        }
    }

    abstract fun linearInterpolation(renderer: ObjectRenderer, timeStamp : Double)

    abstract fun getPlayTime() : Double
}