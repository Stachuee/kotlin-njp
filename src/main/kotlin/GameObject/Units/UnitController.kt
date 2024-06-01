package GameObject.Units

import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer

object UnitController  : Extension{

    override var enabled: Boolean = true

    var unitArray : MutableList<UnitBase> = mutableListOf<UnitBase>()
    var unitsToAdd : MutableList<UnitBase> = mutableListOf<UnitBase>()
    fun addUnit(unit: UnitBase) {
        unitsToAdd.add(unit)
    }

    fun removeUnit(unit: UnitBase) {
        unitArray.remove(unit)
    }

    fun getUnitList() = unitArray

    override fun beforeDraw(drawer: Drawer, program: Program) {
        for(unit in unitArray){
            unit.innerUnit()
        }
        unitArray.addAll(unitsToAdd)
        unitsToAdd.clear()
    }

}