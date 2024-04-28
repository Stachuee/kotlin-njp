package GameManagers

import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer

object UpdateManager : Extension {

    override var enabled: Boolean = true

    private var managers : MutableList<IUpdate> = mutableListOf()

    fun addListener(manager: IUpdate){
        managers.add(manager)
    }

    fun removeListener(manager: IUpdate){
        managers.remove(manager)
    }

    private fun update(){
        managers.forEach { it.update()}
    }

    override fun beforeDraw(drawer: Drawer, program: Program) {
        update()
    }

}