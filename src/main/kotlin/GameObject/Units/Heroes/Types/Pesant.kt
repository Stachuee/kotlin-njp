package GameObject.Units.Heroes.Types

import GameManagers.BuildingManager
import GameManagers.MapController
import GameManagers.VillageController
import GameObject.GameObject
import GameObject.Units.Buildings.BuildingFarm
import GameObject.Units.Buildings.BuildingMine
import GameObject.Units.Foliage.Foliage
import GameObject.Units.Heroes.HeroBase
import GameObject.Units.Heroes.HeroStates
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

class Pesant(renderer: ObjectRenderer) : HeroBase(renderer){

    private enum class CurrentJob {FOOD_HARVESTING, GOLD_HARVESTING, BUILDING, CARRYING}

    companion object{
        var harvestTime = 3.0
        var buildTime = 5.0
    }

    private var workEnd : Double = 0.0
    private var workDelay = false

    private var goldCarried = 0
    private var foodCarried = 0

    private var currentJob : CurrentJob = CurrentJob.FOOD_HARVESTING
    private var farmToHarvest : BuildingFarm? = null
    private var mineToHarvest : BuildingMine? = null
    private var toBuild : VillageController.BuildOrder? = null

    constructor() : this(ObjectRenderer("characters", Vector2(0.0,0.0), 0)) {
        this.setHP(10.0)
        this.setSpeed(75.0)
    }

    override fun idle() {
        super.idle()

        renderer.animator?.triggerAnimation("idle")

        toBuild = VillageController.build()
        if(toBuild != null){
            currentJob = CurrentJob.BUILDING
            state = HeroStates.WORK
            return
        }

        farmToHarvest = BuildingFarm.farmReadyToHarvest()
        if(farmToHarvest != null)
        {
            currentJob = CurrentJob.FOOD_HARVESTING
            state = HeroStates.WORK
            return
        }

        mineToHarvest = BuildingMine.findClosestMine(getWorldPosition())
        if(mineToHarvest != null)
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
        if(farmToHarvest == null) state = HeroStates.IDLE
        if(getWorldPosition().distanceTo(farmToHarvest!!.getWorldPosition()) < 5){
            renderer.animator?.triggerAnimation("attack")

            if(!workDelay)
            {
                workDelay = true
                workEnd = Time.time + harvestTime
            }
            else if(workEnd < Time.time)
            {
                workDelay = false
                foodCarried += farmToHarvest!!.harvest()
                currentJob = CurrentJob.CARRYING
            }
        }
        else
        {
            renderer.animator?.triggerAnimation("walk")
            moveDirection = (farmToHarvest!!.getWorldPosition() - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }

    fun gatherGold(){
        if(mineToHarvest == null) state = HeroStates.IDLE
        if(getWorldPosition().distanceTo(mineToHarvest!!.getWorldPosition()) < 5){
            renderer.animator?.triggerAnimation("attack")

            if(!workDelay)
            {
                workDelay = true
                workEnd = Time.time + harvestTime
            }
            else if(workEnd < Time.time)
            {
                workDelay = false
                goldCarried += mineToHarvest!!.harvest()
                currentJob = CurrentJob.CARRYING
            }
        }
        else
        {
            renderer.animator?.triggerAnimation("walk")
            moveDirection = (mineToHarvest!!.getWorldPosition() - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }

    fun build(){
        if(toBuild == null) state = HeroStates.IDLE
        if(getWorldPosition().distanceTo(toBuild!!.postion) < 5){
            renderer.animator?.triggerAnimation("attack")
            if(!workDelay)
            {
                workDelay = true
                workEnd = Time.time + buildTime
            }
            else if(workEnd < Time.time)
            {
                workDelay = false
                BuildingManager.buildBuilding(toBuild!!.type, toBuild!!.postion)
                toBuild = null
                state = HeroStates.IDLE
            }
        }
        else
        {
            renderer.animator?.triggerAnimation("walk")
            moveDirection = (toBuild!!.postion - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }

    fun carry(){
        if(getWorldPosition().distanceTo(VillageController.tavern.getWorldPosition()) < 5){
            VillageController.tavern.deposit(goldCarried, foodCarried)
            foodCarried = 0
            goldCarried = 0
            state = HeroStates.IDLE
        }
        else
        {
            renderer.animator?.triggerAnimation("walk")
            moveDirection = (VillageController.tavern.getWorldPosition() - getWorldPosition()).normalized
            setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        }
    }
}