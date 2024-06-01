package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import GameObject.Units.UnitBase

abstract class BuildingBase(renderer: ObjectRenderer) : UnitBase(renderer) {
    var buildingCount = 0;

    init {
        renderer.setPosition(getWorldPosition())
        buildingCount++
    }

}