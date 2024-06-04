package GameObject.Units.Buildings

import GameManagers.VillageController
import GameRenderer.ObjectRenderer
import GameObject.Units.UnitBase
import GameObject.Units.UnitController
import GameRenderer.Animations.AnimationLibrary

abstract class BuildingBase : UnitBase {

    protected var destroyed = false
    protected var building = true

    constructor(renderer: ObjectRenderer) : super(renderer){
        renderer.setPosition(getWorldPosition())
        renderer.addAnimator()
        renderer.animator?.addAnimation("building", AnimationLibrary.buildingProgress.copy())
        renderer.animator?.addAnimation("destroyed", AnimationLibrary.buildingDestroyed.copy())

        renderer.animator?.triggerAnimation("building")

        targetable = false
        UnitController.addUnit(this)
    }

    open fun finishBuilding() {
        building = false
        destroyed = false
        targetable = true
        renderer.animator?.triggerAnimation("build")
    }



    override fun takeDamage(damage: Double) {
        if(destroyed) return
        super.takeDamage(damage)
        if(hp <=0 ){
            destroyed = true
            targetable = false
            renderer.animator?.triggerAnimation("destroyed")
            VillageController.addToRepair(this)
        }
    }
}