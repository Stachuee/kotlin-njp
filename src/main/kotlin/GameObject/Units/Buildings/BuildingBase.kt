package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import GameObject.Units.UnitBase

abstract class BuildingBase(renderer: ObjectRenderer) : UnitBase(renderer) {

    init {
        renderer.setPosition(getWorldPosition())
    }

}