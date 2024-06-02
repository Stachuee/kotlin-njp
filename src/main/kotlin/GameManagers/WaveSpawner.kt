package GameManagers

import GameObject.Units.Enemies.EnemyBuilder
import GameObject.Units.Enemies.EnemyEnum
import SimulationEngine.Time
import Utils.RandomUtils
import org.openrndr.math.Vector2

object WaveSpawner : IUpdate{

    object Waves {
        class Wave(val time : Double, val enemy : EnemyEnum, val count : Int)
        val waves : List<Wave> = listOf(
            Wave(1.0, EnemyEnum.GOBLIN, 2)
        )
    }

    var waveCount = 0
    var spawnRange = 2000.0

    var disabled = false


    override fun update() {
        if(disabled) return
        if(Waves.waves[waveCount].time <= Time.time){
            for (i in 1..Waves.waves[waveCount].count){
                var random : Vector2
                do{
                    random = RandomUtils.getPointInCircle(spawnRange)
                }while (random.length < VillageController.vilageRange )

                EnemyBuilder.placeEnemy(Waves.waves[waveCount].enemy, random)
            }
            waveCount++
            if(waveCount >= Waves.waves.size) disabled = true
        }
    }
}