package GameObject.Units.Heroes

import GameObject.Units.Enemies.EnemyEnum

object HeroStats {
    data class HeroStats (val hp : Double, val damage : Double, val speed : Double, val attackDelay : Double)
    val info : Map<HeroesEnum, HeroStats> = mapOf(
        HeroesEnum.PRISONER to HeroStats(50.0, 15.0, 150.0, 0.0),
        HeroesEnum.MAGE to HeroStats(40.0, 25.0, 90.0, 3.0),
        HeroesEnum.BOWMAN to HeroStats(45.0, 15.0, 125.0, 2.0),
        HeroesEnum.PRIEST to HeroStats(40.0, 15.0, 100.0, 2.0),
        HeroesEnum.KNIGHT to HeroStats(150.0, 25.0, 85.0, 0.0),
        HeroesEnum.GLADIATOR to HeroStats(100.0, 20.0, 100.0, 0.0),
        HeroesEnum.PEASANT to HeroStats(25.0, 0.0, 125.0, 0.0),
        )

}