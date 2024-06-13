package GameObject.Units.Buildings.UnitBuildings

import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingCastle(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(1.0,1.0), 0)){
        renderer.animator!!.addAnimation("build", AnimationLibrary.buildingCastle)
        castleCount++
    }
    companion object{
        var castleCount = 0;
    }

    override fun update() {

    }
    override fun finishBuilding() {
        if(!destroyed) HeroesBuilder.placeHero(HeroesEnum.KNIGHT, getWorldPosition())
        super.finishBuilding()
    }
}