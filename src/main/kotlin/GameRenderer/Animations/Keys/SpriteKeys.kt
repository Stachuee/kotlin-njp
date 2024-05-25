package GameRenderer.Animations.Keys

import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class SpriteKeys : AnimationKeys() {

    class SpriteKey(val atlas: String, val position: Vector2, val timeStamp: Double)

    var spriteChange : MutableList<SpriteKey> = mutableListOf()

    fun addKey(key : SpriteKey) : SpriteKeys {
        spriteChange.add(key)
        spriteChange.sortBy { it.timeStamp }
        return this
    }

    override fun linearInterpolation(renderer: ObjectRenderer, timeStamp: Double) {
        if(spriteChange.isEmpty() || timeStamp < spriteChange[0].timeStamp) return

        val first = spriteChange.last { it.timeStamp <= timeStamp }
        val second = spriteChange.first {it.timeStamp > timeStamp }

        if(timeStamp > spriteChange.last().timeStamp) renderer.material.setSprite(second.atlas, second.position)
        renderer.material.setSprite(first.atlas, first.position)
    }

    override fun getPlayTime(): Double {
        return spriteChange.last().timeStamp
    }

}