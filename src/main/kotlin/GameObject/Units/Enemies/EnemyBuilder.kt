package GameObject.Units.Enemies

import GameObject.Units.Enemies.Mele.*
import GameObject.Units.UnitBase
import org.openrndr.math.Vector2


object EnemyBuilder {

    var freeEnemies : MutableList<EnemyBase> = mutableListOf()

    fun returnEnemy(enemy: EnemyBase){
        freeEnemies.add(enemy)
    }

    fun placeEnemy(enemy : EnemyEnum, position: Vector2) : UnitBase {

        var returnValue : EnemyBase? = null
        for(en in freeEnemies){
            if(en.enemyType == enemy)
            {
                returnValue = en
                break
            }
        }

        if(returnValue != null){
            freeEnemies.remove(returnValue)
            returnValue.reset(position)
            return returnValue
        }

        return when(enemy)
        {
            EnemyEnum.GOBLIN -> {
                Goblin().setUnitPosition(position)
            }

            EnemyEnum.HYDRA -> Hydra().setUnitPosition(position)
            EnemyEnum.IMP -> Imp().setUnitPosition(position)
            EnemyEnum.SKELETON -> Skeleton().setUnitPosition(position)
            EnemyEnum.ZOMBIE -> Zombie().setUnitPosition(position)
        }
    }
}