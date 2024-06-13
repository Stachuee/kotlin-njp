package InputSystem

import SimulationEngine.Time
import org.openrndr.*
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2


object InputController :Extension {

    override var enabled: Boolean = true

    private var mousePosition : Vector2 = Vector2.ZERO

    private var horizontalAxis: Double = 0.0
    private var verticalAxis: Double = 0.0
    private var mouseScrollAxis: Double = 0.0


    //<editor-fold desc="Setup">
    override fun setup(program: Program) {

        program.mouse.buttonDown.listen {
            for (listener in mouseListener) {
                listener.mouseDown(it.button)
            }
        }

        program.mouse.buttonUp.listen {
            for (listener in mouseListener) {
                listener.mouseUp(it.button)
            }
        }

        /*
        program.mouse.scrolled.listen{
            mouseScrollAxis = Time.deltaTime * it.rotation.y
        }
         */

        program.keyboard.keyDown.listen {
            when(it.name){
                up -> verticalAxis -= 1.0
                down -> verticalAxis += 1.0
                right -> horizontalAxis += 1.0
                left -> horizontalAxis -= 1.0
            }
            for (listener in keyboardListener) {
                listener.keyDown(it.name)
            }
        }

        program.keyboard.keyUp.listen {
            when(it.name){
                up -> verticalAxis += 1.0
                down -> verticalAxis -= 1.0
                right -> horizontalAxis -= 1.0
                left -> horizontalAxis += 1.0
            }
            for (listener in keyboardListener) {
                listener.keyUp(it.name)
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Update">
    override fun beforeDraw(drawer: Drawer, program: Program) {

        mousePosition = program.mouse.position

    }

    override fun afterDraw(drawer: Drawer, program: Program) {
        mouseScrollAxis = 0.0
    }
    //</editor-fold>

    //<editor-fold desc="ListnerFunc">

    private var keyboardListener : MutableList<IKeyboard> = mutableListOf()
    private var mouseListener : MutableList<IMouseButton> = mutableListOf()


    fun addKeyboardListener(listener: IKeyboard) {
        keyboardListener.add(listener)
    }

    fun removeKeyboardListener(listener: IKeyboard) {
        keyboardListener.remove(listener)
    }

    fun addMouseListener(listener: IMouseButton) {
        mouseListener.add(listener)
    }

    fun removeMouseListener(listener: IMouseButton) {
        mouseListener.remove(listener)
    }
    //</editor-fold>

    //<editor-fold desc="Axis">
    fun getAxis(name : String) : Double
    {
        if (name == "horizontal") {
            return horizontalAxis
        }
        else if (name == "vertical") {
            return verticalAxis
        }
        return 0.0
    }
    fun getScrollAxis() = mouseScrollAxis
    //</editor-fold>

    fun getScreenMousePosition() = mousePosition
}