package GameObject.Units.Heroes

import GameObject.Units.Heroes.Types.*
import GameObject.Units.UnitBase
import org.openrndr.math.Vector2

object HeroesBuilder {

    fun placeHero(character : HeroesEnum, position: Vector2) : UnitBase {
        return when(character)
        {
            HeroesEnum.PEASANT -> Pesant().setUnitPosition(position)
            HeroesEnum.PRISONER -> Prisoner().setUnitPosition(position)
            HeroesEnum.BOWMAN -> Bowman().setUnitPosition(position)
            HeroesEnum.GLADIATOR -> Gladiator().setUnitPosition(position)
            HeroesEnum.KNIGHT -> Knight().setUnitPosition(position)
            HeroesEnum.MAGE -> Mage().setUnitPosition(position)
            HeroesEnum.PRIEST -> Priest().setUnitPosition(position)
        }
    }
}