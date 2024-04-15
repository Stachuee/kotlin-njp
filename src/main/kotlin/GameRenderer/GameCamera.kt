package GameRenderer

import Assets
import InputSystem.InputController
import SimulationEngine.Time
import org.openrndr.Extension
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.tint
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

class GameCamera private constructor(): Extension{

    override var enabled: Boolean = true

    private val atlasUnitSize = 158.0

    private var camZoom = 1.0

    //val gameworldUnitScale = 0.5
    private var gameCameraSize  = Vector2(100.0,100.0)

    private var toRender : MutableList<ObjectRenderer> = arrayListOf()


    private var drawerWorldPosition : Vector2 = Vector2(0.0, 0.0)

    //<editor-fold desc="Singleton">
    companion object {

        @Volatile
        private var instance: GameCamera? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: GameCamera().also { instance = it }
            }
    }
    //</editor-fold>

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
        val input = InputController.getInstance();
        drawerWorldPosition += Vector2(input.getAxis("horizontal"), input.getAxis("vertical")) * camSpeed * Time.getInstance().deltaTime
    }

    fun changeCamZoom()
    {
        val input = InputController.getInstance()
        camZoom += input.getScrollAxis() * camZoomSpeed * Time.getInstance().deltaTime
        if(camZoom < minZoom) camZoom = minZoom
        else if(camZoom > maxZoom) camZoom = maxZoom
    }


    //</editor-fold>

    //<editor-fold desc="Render">
    fun render(drawer: Drawer, program: Program){
        for (renderer in toRender)
        {
            val pos = worldToScreenPosition(renderer.getPostion())
            if(pos.x < 0 ||
                pos.x > gameCameraSize.x ||
                pos.y < 0 ||
                pos.y > gameCameraSize.y) {
                continue
            }

            val mat = renderer.getMaterial()
            drawer.drawStyle.colorMatrix = tint(mat.getTint())
            Assets.get(mat.getAtlasName())?.let { drawer.image(it,
                Rectangle(
                    mat.getAtlasPosition(),
                    atlasUnitSize),
                Rectangle(
                    pos - atlasUnitSize * 1/2 * camZoom ,
                    atlasUnitSize * camZoom))
            }
        }
    }
    //</editor-fold>

}