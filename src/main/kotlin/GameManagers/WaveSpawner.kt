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
            Wave(30.0, EnemyEnum.GOBLIN, 2),
            Wave(60.0, EnemyEnum.GOBLIN, 5),
            Wave(90.0, EnemyEnum.GOBLIN, 5),
            Wave(120.0, EnemyEnum.SKELETON, 2),
            Wave(150.0, EnemyEnum.ZOMBIE, 3),
            Wave(180.0, EnemyEnum.IMP, 10),
            Wave(210.0, EnemyEnum.ZOMBIE, 5),
            Wave(210.0, EnemyEnum.GOBLIN, 2),
            Wave(240.0, EnemyEnum.IMP, 7),
            Wave(240.0, EnemyEnum.GOBLIN, 7),
            Wave(270.0, EnemyEnum.HYDRA, 1),
            Wave(300.0, EnemyEnum.IMP, 3),
            Wave(330.0, EnemyEnum.HYDRA, 2),
            Wave(330.0, EnemyEnum.ZOMBIE, 5),
            Wave(360.0, EnemyEnum.HYDRA, 5),
            Wave(360.0, EnemyEnum.HYDRA, 10),
            Wave(360.0, EnemyEnum.HYDRA, 20),
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