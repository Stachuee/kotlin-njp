package GameObject.Units.Buildings.ProductionBuildings

import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameRenderer.Animations.AnimationLibrary
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

class BuildingHouse(renderer: ObjectRenderer) : BuildingBase(renderer){

    constructor() : this(ObjectRenderer("buildings", Vector2(0.0,0.0), 0)){
        allHouses.add(this)
        renderer.animator!!.addAnimation("build", AnimationLibrary.buildingHouse)
    }

    companion object{
        val allHouses : MutableList<BuildingHouse> = mutableListOf()
    }

    override fun update() {

    }

    override fun finishBuilding() {
        if(!destroyed) HeroesBuilder.placeHero(HeroesEnum.PEASANT, getWorldPosition())
        super.finishBuilding()
    }
}