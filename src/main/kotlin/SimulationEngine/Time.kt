package SimulationEngine

import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.draw.Drawer

object Time : Extension{

    var deltaTime: Double = 0.0
    var lastFrame: Double = 0.0
    override var enabled: Boolean = true


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