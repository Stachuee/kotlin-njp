package Utils

import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object RandomUtils {

    fun getPointInCircle(range : Double = 1.0) : Vector2{

        val t = 2 * PI * Random.double(0.0, 1.0)
        val u = Random.double(0.0, 1.0) + Random.double(0.0, 1.0)
        val r = if(u > 1) 2 - u else u
        return Vector2((range * r * cos(t)), (range * r * sin(t)))
    }
}