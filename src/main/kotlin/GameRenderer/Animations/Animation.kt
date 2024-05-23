package GameRenderer.Animations

import GameRenderer.Animations.Keys.AnimationKeys
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.extra.shapes.regularPolygon
import kotlin.math.abs

class Animation {
    val animationKeys : MutableList<AnimationKeys> = mutableListOf()

    var endingTime = 0.0
    var startTime = 0.0

    fun addAnimationKeys(keys : AnimationKeys) : Animation{
        animationKeys.add(keys)
        return this
    }

    fun startAnimation(){
        animationKeys.forEach {
            val time = it.getPlayTime()
            if(time > endingTime) endingTime = time
        }
        endingTime += Time.time
        startTime = Time.time
    }

    fun playAnimation(renderer: ObjectRenderer) : Boolean{
        if(endingTime <= Time.time) return false
        val progress = (Time.time - startTime)

        animationKeys.forEach{
            it.evaluate(renderer, progress)
        }


        return true
    }
}
