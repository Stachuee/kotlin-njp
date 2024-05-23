package GameObject.Units.Buildings

import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.math.Vector2

class BuildingFarm (renderer: ObjectRenderer) : BuildingBase(renderer){

    var matureTime = 0.0
    var growTime = 10.0
    var growing = false
    var readyToHarvest = false

    constructor() : this(ObjectRenderer("buildings", Vector2(1.0,2.0), 0)) {
        renderer.setPosition(getWorldPosition())
    }

    override fun update() {
        if(growing)
        {
            if(matureTime <= Time.time)
            {
                readyToHarvest = true
                growing = false
            }
        }
    }

    fun plant(){
        matureTime = Time.time + growTime
        growing = true
    }

    fun harvest(){
        readyToHarvest = false
        growing = false
    }
}