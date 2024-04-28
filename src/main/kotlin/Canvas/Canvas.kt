package Canvas

import Assets
import GameRenderer.RendererType
import InputSystem.InputController
import InputSystem.IMouseButton
import org.openrndr.Extension
import org.openrndr.MouseButton
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.tint
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

object Canvas : Extension, IMouseButton {
    override var enabled: Boolean = true

    private var canvasObjects : MutableList<CanvasObject> = mutableListOf()


    override fun setup(program: Program) {
        InputController.addMouseListener(this)

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

            val mousePosition = InputController.getScreenMousePosition()

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

    }

    override fun mouseUp(event: MouseButton) {
        if(event.name == "LEFT")
        {
            hoverTarget?.onClick()
        }
    }
    //</editor-fold>

    //<editor-fold desc="Draw">
    private fun renderCanvas(drawer: Drawer)
    {
        sortRenderers()
        for (canvasObject in canvasObjects){
            when(canvasObject.renderer.rendererType)
            {
                RendererType.TEXT -> {
                    val mat = canvasObject.renderer.material
                    drawer.drawStyle.colorMatrix = tint(mat.getTint())
                    drawer.text(canvasObject.renderer.text, canvasObject.canvasPosition)
                }
                RendererType.IMAGE -> {
                    val mat = canvasObject.renderer.material
                    drawer.drawStyle.colorMatrix = tint(mat.getTint())
                    Assets.get(mat.getAtlasName())?.let { drawer.image(it,
                        Rectangle(
                            mat.getAtlasPosition(),
                            canvasObject.renderer.material.atlasUnitSize,
                            canvasObject.renderer.material.atlasUnitSize),
                        Rectangle(
                            canvasObject.canvasPosition ,
                            canvasObject.canvasSize.x,
                            canvasObject.canvasSize.y))
                    }
                }
            }

        }

    }

    private fun sortRenderers()
    {
        canvasObjects.sortWith(compareByDescending {it.renderer.sortingLayer})
    }

    //</editor-fold>

    fun instantiateCanvasObject(CanvasObject: CanvasObject)
    {
        canvasObjects.add(CanvasObject)
    }


}