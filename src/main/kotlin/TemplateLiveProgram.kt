
import Canvas.Buttons.BuildButton
import Canvas.Canvas
import GameManagers.BuildingManager
import GameManagers.Map
import GameManagers.ResourceManager
import GameManagers.UpdateManager
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import InputSystem.InputController
import SimulationEngine.Time
import GameObject.Units.UnitController
import GameRenderer.Material
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage
import org.openrndr.math.Vector2


var Assets : kotlin.collections.Map<String, ColorBuffer> = mapOf<String, ColorBuffer>()

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

        extend(UnitController)
        extend(Time)
        extend(InputController)
        extend(UpdateManager)
        extend(Canvas)
        extend(GameCamera)

        UpdateManager.addListener(BuildingManager)
        UpdateManager.addListener(ResourceManager)

        val mat = Material(Vector2(0.0,0.0), "buildings")
        Canvas.instantiateCanvasObject(
            BuildButton(ObjectRenderer(mat, 0), Vector2(0.0, 0.0), Vector2(160.0, 160.0))
        )

        InputController.addMouseListener(BuildingManager)

        Map.generateMap(Vector2(5000.0,5000.0))
    }
}

fun loadAssets(): kotlin.collections.Map<String, ColorBuffer> {
    val map = HashMap<String, ColorBuffer>()

    map["buildings"] = loadImage( "openrndr-template/src/main/resources/buildings.png")
    map["characters"] = loadImage( "openrndr-template/src/main/resources/characters.png")
    map["enemies"] = loadImage( "openrndr-template/src/main/resources/enemies.png")
    map["foliage"] = loadImage( "openrndr-template/src/main/resources/foliage.png")
    map["UIOne"] = loadImage( "openrndr-template/src/main/resources/UIOne.png")

    return map
}
