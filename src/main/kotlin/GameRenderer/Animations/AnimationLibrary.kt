package GameRenderer.Animations

import GameRenderer.Animations.Keys.RotationKeys

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
}