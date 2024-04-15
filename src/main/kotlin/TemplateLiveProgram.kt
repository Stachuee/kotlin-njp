
import Canvas.Button
import Canvas.Canvas
import GameManagers.BuildingBuilder
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import InputSystem.InputController
import SimulationEngine.Time
import GameObject.Units.Buildings.BuildingBase
import GameObject.Units.Buildings.BuildingHouse
import GameObject.Units.UnitController
import GameRenderer.Material
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.Vector2
import org.openrndr.panel.ControlManager
import org.openrndr.panel.elements.button
import org.openrndr.panel.elements.clicked
import org.openrndr.panel.layout


var Assets : Map<String, ColorBuffer> = mapOf<String, ColorBuffer>()

fun main() = application {
    configure {
        width = 800
        height = 800


    }
    program {
        Assets = loadAssets()

        extend {
            drawer.clear(ColorRGBa.BLACK)
        }

        extend(UnitController.getInstance())
        extend(Time.getInstance())
        extend(InputController.getInstance())
        extend(Canvas.getInstance())
        extend(GameCamera.getInstance())

        val mat = Material(Vector2(1.0,0.0), "buildings")
        Canvas.getInstance().instantiateCanvasObject(
            Button(ObjectRenderer(mat, 0), Vector2(0.0, 0.0), Vector2(158.0, 158.0)))
        InputController.getInstance().addMouseListener(BuildingBuilder.getInstance())
    }
}

fun loadAssets(): Map<String, ColorBuffer> {
    val map = HashMap<String, ColorBuffer>()

    map["buildings"] = loadImage( "openrndr-template/src/main/resources/buildings.png")
    map["characters"] = loadImage( "openrndr-template/src/main/resources/characters.png")
    map["enemies"] = loadImage( "openrndr-template/src/main/resources/enemies.png")
    map["foliage"] = loadImage( "openrndr-template/src/main/resources/foliage.png")
    map["UIOne"] = loadImage( "openrndr-template/src/main/resources/UIOne.png")

    return map
}
