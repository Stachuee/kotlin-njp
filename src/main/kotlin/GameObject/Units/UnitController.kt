package GameObject.Units

import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer

object UnitController  : Extension{

    override var enabled: Boolean = true

    var unitArray : MutableList<UnitBase> = mutableListOf<UnitBase>()
    var unitsToAdd : MutableList<UnitBase> = mutableListOf<UnitBase>()
    var unitsToRemove : MutableList<UnitBase> = mutableListOf<UnitBase>()

    var enemyArray : MutableList<UnitBase> = mutableListOf<UnitBase>()
    var enemiesToAdd : MutableList<UnitBase> = mutableListOf<UnitBase>()

    var projectileArray : MutableList<Projectile> = mutableListOf<Projectile>()
    var projectilesToAdd : MutableList<Projectile> = mutableListOf<Projectile>()
    var projectileToRemove : MutableList<Projectile> = mutableListOf<Projectile>()


    fun addEnemy(unit: UnitBase){
        enemiesToAdd.add(unit)
    }

    fun addProjectile(projectile: Projectile){
        projectilesToAdd.add(projectile)
    }

    fun removeProjectile(projectile: Projectile){
        projectileToRemove.add(projectile)
        ProjectileBuilder.returnProjectile(projectile)
    }

    fun addUnit(unit: UnitBase) {
        unitsToAdd.add(unit)
    }

    fun removeUnit(unit: UnitBase) {
        unitsToRemove.add(unit)
    }

    fun getUnitList() = unitArray


    override fun beforeDraw(drawer: Drawer, program: Program) {
        for(unit in unitArray){
            unit.innerUnit()
        }
        unitArray.addAll(unitsToAdd)
        unitArray.removeAll(unitsToRemove)
        unitsToAdd.clear()
        unitsToRemove.clear()

        for (unit in enemyArray){
            unit.innerUnit()
        }
        enemyArray.addAll(enemiesToAdd)
        enemiesToAdd.clear()

        for(projectile in projectileArray){
            projectile.update()
        }
        projectileArray.addAll(projectilesToAdd)
        projectileArray.removeAll(projectileToRemove)
        projectileToRemove.clear()
        projectilesToAdd.clear()
    }


}