package GameRenderer.Animations

import GameRenderer.Animations.Keys.AnimationKeys
import GameRenderer.Animations.Keys.RotationKeys
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.extra.shapes.regularPolygon
import kotlin.math.abs

class Animation {
    val animationKeys : MutableList<AnimationKeys> = mutableListOf()

    var endingTime = 0.0
    var startTime = 0.0
    var looping = false

    fun addAnimationKeys(keys : AnimationKeys) : Animation{
        animationKeys.add(keys)
        return this
    }
    fun addAnimationKeys(keys : List<AnimationKeys>) : Animation{
        animationKeys.addAll(keys)
        return this
    }

    fun setLooping(newLooping: Boolean): Animation{
        looping = newLooping
        return this
    }

    fun startAnimation(){
        endingTime = 0.0
        animationKeys.forEach {
            val time = it.getPlayTime()
            if(time > endingTime) endingTime = time
        }
        endingTime += Time.time
        startTime = Time.time
    }

    fun playAnimation(renderer: ObjectRenderer) : Boolean{
        if(endingTime <= Time.time){
            if (looping) {
                startAnimation()
            }
            else return false
        }
        val progress = (Time.time - startTime)

        animationKeys.forEach{
            it.evaluate(renderer, progress)
        }


        return true
    }

    fun copy() : Animation {
        return Animation()
            .setLooping(looping)
            .addAnimationKeys(
                animationKeys
            )
    }
}
