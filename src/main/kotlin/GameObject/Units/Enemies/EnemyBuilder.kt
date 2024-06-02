package GameObject.Units.Enemies

import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.Heroes.Types.Pesant
import GameObject.Units.Heroes.Types.SpearWarrior
import GameObject.Units.UnitBase
import org.openrndr.math.Vector2


object EnemyBuilder {
    fun placeEnemy(enemy : EnemyEnum, position: Vector2) : UnitBase {
        return when(enemy)
        {
            EnemyEnum.GOBLIN -> {
                Goblin().setUnitPosition(position)
            }
        }
    }
}