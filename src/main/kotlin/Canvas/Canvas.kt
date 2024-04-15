package Canvas

import Assets
import GameRenderer.ObjectRenderer
import InputSystem.InputController
import InputSystem.MouseInterface
import org.openrndr.Extension
import org.openrndr.MouseButton
import org.openrndr.Program
import org.openrndr.draw.Drawer
import org.openrndr.draw.tint
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

class Canvas : Extension, MouseInterface {
    override var enabled: Boolean = true
    //<editor-fold desc="Singleton">
    companion object {

        @Volatile
        private var instance: Canvas? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Canvas().also { instance = it }
            }
    }
    //</editor-fold>

    private var canvasObjects : MutableList<CanvasObject> = mutableListOf()


    override fun setup(program: Program) {
        InputController.getInstance().addMouseListener(this)

    }

    override fun afterDraw(drawer: Drawer, program: Program) {
        renderCanvas(drawer)
        checkHover()
    }

    //<editor-fold desc="Events">
    var hoverTarget : CanvasObject? = null

    fun checkHover(){
        for (canvasObject in canvasObjects) {
            if(!canvasObject.raycastTarget) continue
            val canvasPos = canvasObject.canvasPosition
            val canvasSize = canvasObject.canvasSize

            val mousePosition = InputController.getInstance().getScreenMousePosition()

            if(mousePosition.x < canvasPos.x || mousePosition.y < canvasPos.y) continue
            if(mousePosition.x > canvasPos.x + canvasSize.x || mousePosition.y > canvasPos.y + canvasSize.y) continue


            if(canvasObject != hoverTarget)
            {
                hoverTarget?.onHoverExit()
                hoverTarget = canvasObject
                hoverTarget!!.onHoverEnter()
            }
            hoverTarget = canvasObject
            return
        }
        if(hoverTarget != null)
        {
            hoverTarget?.onHoverExit()
            hoverTarget = null
        }
    }

    override fun mouseDown(event: MouseButton) {
        if(event.name == "LEFT")
        {
            hoverTarget?.onClick()
        }
    }

    override fun mouseUp(event: MouseButton) {

    }
    //</editor-fold>

    //<editor-fold desc="Draw">
    fun renderCanvas(drawer: Drawer)
    {
        for (canvasObject in canvasObjects){
            val mat = canvasObject.renderer.getMaterial()
            drawer.drawStyle.colorMatrix = tint(mat.getTint())
            Assets.get(mat.getAtlasName())?.let { drawer.image(it,
                Rectangle(
                    mat.getAtlasPosition(),
                    canvasObject.canvasSize.x,
                    canvasObject.canvasSize.y),
                Rectangle(
                    canvasObject.canvasPosition ,
                    canvasObject.canvasSize.x,
                    canvasObject.canvasSize.y))
            }
        }
    }
    //</editor-fold>

    fun instantiateCanvasObject(CanvasObject: CanvasObject)
    {
        canvasObjects.add(CanvasObject)
    }


}