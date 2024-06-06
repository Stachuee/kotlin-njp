package GameObject.Units.Buildings.UnitBuildings

import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingArena (renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(1.0,1.0), 0)){
        renderer.animator!!.addAnimation("build", AnimationLibrary.buildingArena)
    }

    override fun update() {

    }

    override fun finishBuilding() {
        if(!destroyed) HeroesBuilder.placeHero(HeroesEnum.GLADIATOR, getWorldPosition())
        super.finishBuilding()
    }
}