package Utils

import org.openrndr.math.Vector2
import org.openrndr.math.Vector3
import kotlin.math.PI
import kotlin.math.atan2


object MathUtils {
    fun rotateTo(from : Vector2, to : Vector2) : Double{
        val targ = to - from
        val angle = atan2(targ.y, targ.x) * (180/ PI)
        return angle
    }
}