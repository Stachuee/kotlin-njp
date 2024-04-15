package SimulationEngine

import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer

class Time private constructor() : Extension{

    var deltaTime: Double = 0.0
    var lastFrame: Double = 0.0
    override var enabled: Boolean = true

    //<editor-fold desc="Singleton">
    companion object {

        @Volatile
        private var instance: Time? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Time().also { instance = it }
            }
    }
    //</editor-fold>

    fun updateClock()
    {
        deltaTime = (System.currentTimeMillis() - lastFrame) / 1000
        lastFrame = System.currentTimeMillis().toDouble()
    }

    override fun beforeDraw(drawer: Drawer, program: Program) {
        updateClock()
    }

    override fun setup(program: Program) {
        deltaTime = 0.0
        lastFrame = System.currentTimeMillis().toDouble()
    }
}