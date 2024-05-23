package GameRenderer

import Assets
import GameManagers.MapController
import InputSystem.InputController
import SimulationEngine.Time
import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.tint
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

object GameCamera : Extension{

    override var enabled: Boolean = true

    private var camZoom = 1.0

    //val gameworldUnitScale = 0.5
    private var gameCameraSize  = Vector2(100.0,100.0)

    private var toRender : MutableList<ObjectRenderer> = arrayListOf()


    private var drawerWorldPosition : Vector2 = Vector2(0.0, 0.0)


    //<editor-fold desc="RenderedObjects">
    fun addRenderer(spriteRenderer: ObjectRenderer) {
        toRender.add(spriteRenderer)
    }

    fun removeRenderer(spriteRenderer: ObjectRenderer) {
        toRender.remove(spriteRenderer)
    }
    //</editor-fold>

    //<editor-fold desc="CameraTransformations">


    fun screenToWorldPosition(screenPosition : Vector2) : Vector2{
        return ((screenPosition - Vector2(gameCameraSize.x * 1/2, gameCameraSize.y * 1/2) + (drawerWorldPosition )) * (1/camZoom))
    }

    fun worldToScreenPosition(worldPosition : Vector2) : Vector2{
        return worldPosition * camZoom - (drawerWorldPosition ) + Vector2(gameCameraSize.x * 1/2, gameCameraSize.y * 1/2)
    }
    //</editor-fold>


    override fun setup(program: Program) {
        this.gameCameraSize = Vector2(program.width.toDouble(), program.height.toDouble())
    }

    override fun beforeDraw(drawer: Drawer, program: Program) {
        moveCam()
        changeCamZoom()
    }

    override fun afterDraw(drawer: Drawer, program: Program) {
        render(drawer, program)
    }

    //<editor-fold desc="CameraMovment">
    val camSpeed = 200.0
    val camZoomSpeed = 100

    val minZoom = 0.2
    val maxZoom = 2.0

    fun moveCam()
    {
        val input = InputController;
        drawerWorldPosition += Vector2(input.getAxis("horizontal"), input.getAxis("vertical")) * camSpeed * Time.deltaTime
    }

    fun changeCamZoom()
    {
        val input = InputController
        camZoom += input.getScrollAxis() * camZoomSpeed * Time.deltaTime
        if(camZoom < minZoom) camZoom = minZoom
        else if(camZoom > maxZoom) camZoom = maxZoom
    }


    //</editor-fold>

    //<editor-fold desc="Render">

    private fun render(drawer: Drawer, program: Program){
        sortRenderers()
        for (renderer in toRender)
        {
            if (!renderer.rendererActive) continue
            drawer.pushTransforms()
            renderer.animator?.updateAnimation(renderer)
            val mat = renderer.material
            val pos = worldToScreenPosition(renderer.getPostion())


            if(pos.x + mat.atlasUnitSize < 0 ||
                pos.x - mat.atlasUnitSize > gameCameraSize.x ||
                pos.y + mat.atlasUnitSize < 0 ||
                pos.y - mat.atlasUnitSize > gameCameraSize.y) {
                continue
            }

            drawer.drawStyle.colorMatrix = tint(mat.getTint())


            drawer.translate(pos)
            drawer.rotate(renderer.getRotation())
            drawer.scale(renderer.getScale().x, renderer.getScale().y)

            Assets.get(mat.getAtlasName())?.let { drawer.image(it,
                Rectangle(
                    mat.getAtlasPosition(),
                    mat.atlasUnitSize),
                Rectangle(
                    Vector2.ZERO - mat.atlasUnitSize * 1/2 * camZoom,
                    mat.atlasUnitSize * camZoom))
            }

            drawer.popTransforms()

            /*
            drawer.pushTransforms()
            drawer.translate(pos )
            drawer.circle(Vector2.ZERO, 10.0)
            drawer.popTransforms()
            */
        }
    }

    private fun sortRenderers()
    {
        toRender.sortWith(compareByDescending {it.sortingLayer })
    }

    //</editor-fold>

}