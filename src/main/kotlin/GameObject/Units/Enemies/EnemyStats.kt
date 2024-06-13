package GameObject.Units.Enemies

import GameObject.Units.Buildings.BuildingEnum
import GameObject.Units.Buildings.BuildingInfo.BuildingInfo
import org.openrndr.math.IntVector2

object EnemyStats {
    data class EnemyStats (val hp : Double, val damage : Double, val speed : Double)

    val info : Map<EnemyEnum, EnemyStats> = mapOf(
        EnemyEnum.GOBLIN to EnemyStats(15.0, 15.0, 100.0),
        EnemyEnum.HYDRA to EnemyStats(300.0, 30.0, 75.0),
        EnemyEnum.IMP to EnemyStats(25.0, 20.0, 125.0),
        EnemyEnum.SKELETON to EnemyStats(40.0, 15.0, 100.0),
        EnemyEnum.ZOMBIE to EnemyStats(75.0, 20.0, 75.0),
    )

}