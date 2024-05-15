package GameObject.Units.Heroes

import GameObject.Units.Heroes.Types.Pesant
import GameObject.Units.UnitBase
import org.openrndr.math.Vector2

object HeroesBuilder {

    fun placeHero(character : HeroesEnum, position: Vector2) : UnitBase {
        return when(character)
        {
            HeroesEnum.PEASANT -> {
                Pesant().setUnitPosition(position)
            }
            HeroesEnum.WARRIOR -> {
                Pesant().setUnitPosition(position)
            }
        }
    }
}