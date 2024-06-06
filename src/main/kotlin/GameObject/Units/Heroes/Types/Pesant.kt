package GameObject.Units.Heroes.Types

import GameManagers.VillageController
import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Buildings.ProductionBuildings.BuildingFarm
import GameObject.Units.Buildings.ProductionBuildings.BuildingMine
import GameObject.Units.Buildings.ProductionBuildings.BuildingWarehouse
import GameObject.Units.Heroes.HeroBase
import GameObject.Units.Heroes.HeroStates
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import org.openrndr.math.Vector2

class Pesant(renderer: ObjectRenderer) : HeroBase(renderer){

    private enum class CurrentJob {FOOD_HARVESTING, GOLD_HARVESTING, BUILDING, CARRYING}

    companion object{
        var harvestTime = 3.0
        var buildTime = 5.0

        val allPesants : MutableList<Pesant> = mutableListOf()
    }

    private var workEnd : Double = 0.0
    private var workDelay = false

    private var goldCarried = 0
    private var foodCarried = 0

    private var currentJob : CurrentJob = CurrentJob.FOOD_HARVESTING
    private var workTarget : BuildingBase? = null

    constructor() : this(ObjectRenderer("characters", Vector2(0.0,0.0), 0)) {
        this.setHP(10.0)
        this.setSpeed(75.0)
        allPesants.add(this)
    }

    override fun idle() {
        super.idle()
        workTarget = null

        renderer.animator?.triggerAnimation("idle")

        workTarget = VillageController.build()
        if(workTarget != null){
            currentJob = CurrentJob.BUILDING
            state = HeroStates.WORK
            return
        }

        workTarget = BuildingFarm.farmReadyToHarvest()
        if(workTarget != null)
        {
            currentJob = CurrentJob.FOOD_HARVESTING
            state = HeroStates.WORK
            return
        }

        workTarget = BuildingMine.findClosestMine(getWorldPosition())
        if(workTarget != null)
        {
            currentJob = CurrentJob.GOLD_HARVESTING
            state = HeroStates.WORK
            return
        }


        /*
        if (nextWanderDirectionChange < Time.time) {
            nextWanderDirectionChange = Random.double(4.0, 6.0) + Time.time

            moveDirection = if(Random.double(0.0, 1.0) < idleChance)  Vector2.ZERO
            else ((housePosition + Vector2(Random.double(-wanderDistance, wanderDistance), Random.double(-wanderDistance, wanderDistance))) - getWorldPosition()).normalized
        }
        setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        */
    }

    override fun work() {
        super.work()
        when(currentJob){
            CurrentJob.FOOD_HARVESTING -> gatherFood()
            CurrentJob.GOLD_HARVESTING -> gatherGold()
            CurrentJob.BUILDING -> build()
            CurrentJob.CARRYING -> carry()
        }
    }

    fun gatherFood(){
        if(workTarget == null) {
            state = HeroStates.IDLE
            return
        }
        if(getWorldPosition().distanceTo(workTarget!!.getWorldPosition()) < 50){
            renderer.animator?.triggerAnimation("attack")

            if(!workDelay)
            {
                workDelay = true
                workEnd = Time.time + harvestTime
            }
            else if(workEnd < Time.time)
            {
                workDelay = false
                foodCarried += (workTarget as BuildingFarm).harvest()
                currentJob = CurrentJob.CARRYING
                workTarget = null
            }
        }
        else
        {
            walkToTarget()
        }
    }

    fun gatherGold(){
        if(workTarget == null) {
            state = HeroStates.IDLE
            return
        }
        if(getWorldPosition().distanceTo(workTarget!!.getWorldPosition()) < 50){
            renderer.animator?.triggerAnimation("attack")

            if(!workDelay)
            {
                workDelay = true
                workEnd = Time.time + harvestTime
            }
            else if(workEnd < Time.time)
            {
                workDelay = false
                goldCarried += (workTarget as BuildingMine).harvest()
                currentJob = CurrentJob.CARRYING
                workTarget = null
            }
        }
        else
        {
            walkToTarget()
        }
    }

    fun build(){
        if(workTarget == null) {
            state = HeroStates.IDLE
            return
        }
        if(getWorldPosition().distanceTo(workTarget!!.getWorldPosition()) < 50){
            renderer.animator?.triggerAnimation("attack")
            if(!workDelay)
            {
                workDelay = true
                workEnd = Time.time + buildTime
            }
            else if(workEnd < Time.time)
            {
                workTarget?.finishBuilding()
                workDelay = false
                workTarget = null
                state = HeroStates.IDLE
            }
        }
        else
        {
            renderer.animator?.triggerAnimation("walk")
            moveDirection = (workTarget!!.getWorldPosition() - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }

    fun carry(){
        if(workTarget == null) {
            workTarget = BuildingWarehouse.findClosestWarehouse(getWorldPosition())
            if(workTarget == null) {
                state = HeroStates.IDLE
                return
            }
        }
        if(getWorldPosition().distanceTo(workTarget!!.getWorldPosition()) < 50){
            (workTarget as BuildingWarehouse).deposit(goldCarried, foodCarried)
            foodCarried = 0
            goldCarried = 0
            state = HeroStates.IDLE
        }
        else
        {
            walkToTarget()
        }
    }

    fun walkToTarget(){
        renderer.animator?.triggerAnimation("walk")
        moveDirection = (workTarget!!.getWorldPosition() - getWorldPosition()).normalized
        setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
    }

}