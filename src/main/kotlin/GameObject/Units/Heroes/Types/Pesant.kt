package GameObject.Units.Heroes.Types

import GameManagers.MapController
import GameObject.GameObject
import GameObject.Units.Foliage.Foliage
import GameObject.Units.Heroes.HeroBase
import GameObject.Units.Heroes.HeroStates
import GameRenderer.ObjectRenderer
import SimulationEngine.Time
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2

class Pesant(renderer: ObjectRenderer) : HeroBase(renderer){


    private var nextWanderDirectionChange = 0.0
    private var housePosition : Vector2 = Vector2.ZERO
    private var wanderDistance = 100.0
    private var idleChance = 0.7
    private var chopTimer = 7.0
    private var workEnd : Double = 0.0

    constructor() : this(ObjectRenderer("characters", Vector2(0.0,0.0), 0)) {
        this.setHP(10.0)
        this.setSpeed(75.0)
    }

    override fun idle() {
        super.idle()
        if (nextWanderDirectionChange < Time.time) {
            nextWanderDirectionChange = Random.double(4.0, 6.0) + Time.time

            moveDirection = if(Random.double(0.0, 1.0) < idleChance)  Vector2.ZERO
            else ((housePosition + Vector2(Random.double(-wanderDistance, wanderDistance), Random.double(-wanderDistance, wanderDistance))) - getWorldPosition()).normalized
        }
        setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
        state = HeroStates.WORK
    }

    override fun work() {
        super.work()
        if(workEnd > Time.time)return
        if(target == null || !target!!.getActive()) target = MapController.getClosestFoliage(getWorldPosition()) as GameObject

        val moveVector = (target!!.getWorldPosition() - getWorldPosition())
        moveDirection = moveVector.normalized

        if(moveVector.length < 3) {
            (target as Foliage).chop()
            workEnd = Time.time + chopTimer
        }

        setUnitPosition(getWorldPosition() + moveDirection * speed * Time.deltaTime)
    }
}