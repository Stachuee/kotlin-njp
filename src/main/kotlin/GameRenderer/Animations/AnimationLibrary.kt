package GameRenderer.Animations

import GameRenderer.Animations.Keys.RotationKeys
import GameRenderer.Animations.Keys.SpriteKeys
import org.openrndr.math.Vector2

object AnimationLibrary {
    val walk = Animation()
        .setLooping(true)
        .addAnimationKeys(
            RotationKeys()
                .addKey(RotationKeys.RotationKey(0.0, 0.0))
                .addKey(RotationKeys.RotationKey(10.0, 0.3))
                .addKey(RotationKeys.RotationKey(0.0, 0.6))
                .addKey(RotationKeys.RotationKey(-10.0, 0.9))
                .addKey(RotationKeys.RotationKey(0.0, 1.2))
        )
    val idle = Animation()
        .setLooping(true)
        .addAnimationKeys(
            RotationKeys()
                .addKey(RotationKeys.RotationKey(0.0, 0.0))
                .addKey(RotationKeys.RotationKey(0.0, 1.0))
        )
    val attack = Animation()
        .setLooping(true)
        .addAnimationKeys(
            RotationKeys()
                .addKey(RotationKeys.RotationKey(0.0, 0.0))
                .addKey(RotationKeys.RotationKey(15.0, 0.1))
                .addKey(RotationKeys.RotationKey(0.0, 0.6))
        )

    val buildingProgress = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,2.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,2.0), 1.0))
        )

    val maturePlant = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(2.0,2.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(2.0,2.0), 1.0))
        )

    val growPlant = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(1.0,2.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(1.0,2.0), 1.0))
        )
}