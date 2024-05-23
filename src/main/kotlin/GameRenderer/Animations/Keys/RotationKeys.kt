package GameRenderer.Animations.Keys

import GameRenderer.ObjectRenderer

class RotationKeys : AnimationKeys() {

    class RotationKey(val rotation : Double, val timeStamp: Double)

    var rotationChange : MutableList<RotationKey> = mutableListOf()

    fun addKey(key : RotationKey) : RotationKeys {
        rotationChange.add(key)
        rotationChange.sortBy { it.timeStamp }
        return this
    }

    override fun linearInterpolation(renderer: ObjectRenderer, timeStamp: Double) {
        if(rotationChange.isEmpty() || timeStamp < rotationChange[0].timeStamp || timeStamp > rotationChange.last().timeStamp) return

        val first = rotationChange.last { it.timeStamp < timeStamp }
        val second = rotationChange.first {it.timeStamp > timeStamp }

        val subtimeStamp = 1 - ((second.timeStamp - timeStamp) / (second.timeStamp - first.timeStamp))
        println((second.timeStamp - first.timeStamp - timeStamp))
        renderer.setRotation(first.rotation + (second.rotation - first.rotation) * subtimeStamp)
    }

    override fun getPlayTime(): Double {
        return rotationChange.last().timeStamp
    }

}