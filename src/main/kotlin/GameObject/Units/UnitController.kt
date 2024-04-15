package GameObject.Units

import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer

class UnitController private constructor() : Extension{

    override var enabled: Boolean = true

    var unitArray : MutableList<UnitBase> = mutableListOf<UnitBase>()

    //<editor-fold desc="Singleton">
    companion object {

        @Volatile
        private var instance: UnitController? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UnitController().also { instance = it }
            }
    }
    //</editor-fold>

    fun addUnit(unit: UnitBase) {
        unitArray.add(unit)
    }

    fun removeUnit(unit: UnitBase) {
        unitArray.remove(unit)
    }

    fun getUnitList() = unitArray

    override fun beforeDraw(drawer: Drawer, program: Program) {
        for(unit in unitArray){
            unit.innerUnit()
        }
    }

}