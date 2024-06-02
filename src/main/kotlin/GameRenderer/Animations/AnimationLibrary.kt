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
                .addKey(RotationKeys.RotationKey(10.0, 0.25))
                .addKey(RotationKeys.RotationKey(0.0, 0.5))
                .addKey(RotationKeys.RotationKey(-10.0, 0.75))
                .addKey(RotationKeys.RotationKey(0.0, 1.0))
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

    val down = Animation()
        .setLooping(true)
        .addAnimationKeys(
            RotationKeys()
                .addKey(RotationKeys.RotationKey(90.0, 0.0))
                .addKey(RotationKeys.RotationKey(95.0, 0.3))
                .addKey(RotationKeys.RotationKey(90.0, 0.6))
                .addKey(RotationKeys.RotationKey(85.0, 0.9))
                .addKey(RotationKeys.RotationKey(90.0, 1.2))
        )

    val buildingProgress = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,2.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,2.0), 1.0))
        )

    val buildingDestroyed = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(3.0,2.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(3.0,2.0), 1.0))
        )

    val buildingHouse = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,0.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,0.0), 1.0))
        )

    val buildingWarehouse = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,1.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(0.0,1.0), 1.0))
        )

    val buildingMine = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(2.0,0.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(2.0,0.0), 1.0))
    )

    val buildingTower = Animation()
        .setLooping(true)
        .addAnimationKeys(
            SpriteKeys()
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(1.0,0.0), 0.0))
                .addKey(SpriteKeys.SpriteKey("buildings", Vector2(1.0,0.0), 1.0))
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