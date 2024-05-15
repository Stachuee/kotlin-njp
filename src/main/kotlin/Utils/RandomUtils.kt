package Utils

import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object RandomUtils {

    fun getPointOnCircle() : Vector2{
        val r = sqrt(Random.double(0.0, 1.0))
        val theta = Random.double(0.0, 1.0) * 2 * PI
        return Vector2(r + cos(theta), r + sin(theta))
    }
}