package Canvas.Buttons

import Canvas.Button
import GameManagers.BuildingManager
import GameObject.Units.Buildings.BuildingEnum
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2


class BuildButton : Button {

    var buildingType : BuildingEnum = BuildingEnum.HOUSE

    constructor(renderer: ObjectRenderer, position: Vector2, size : Vector2) : super(renderer, position, size) {

    }


    override fun onClick() {
        BuildingManager.showGhost(buildingType)
    }
}