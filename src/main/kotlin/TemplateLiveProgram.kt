

import Canvas.Canvas
import GameManagers.*
import GameRenderer.GameCamera

import InputSystem.InputController
import SimulationEngine.Time
import GameObject.Units.UnitController

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage
import org.openrndr.math.Vector2


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

        extend(UnitController)
        extend(Time)
        extend(InputController)
        extend(UpdateManager)
        extend(Canvas)
        extend(GameCamera)

        UpdateManager.addListener(BuildingManager)
        UpdateManager.addListener(ResourceManager)
        UpdateManager.addListener(VillageController)

        /*
        val mat = Material(Vector2(0.0,0.0), "buildings")
        Canvas.instantiateCanvasObject(
            BuildButton(ObjectRenderer(mat, 0), Vector2(0.0, 0.0), Vector2(160.0, 160.0))
        )
         */
        InputController.addMouseListener(BuildingManager)

        MapController.generateMap(Vector2(5000.0,5000.0))
    }
}

fun loadAssets(): Map<String, ColorBuffer> {
    val map = HashMap<String, ColorBuffer>()

    map["buildings"] = loadImage( "src/main/resources/buildings.png")
    map["characters"] = loadImage( "src/main/resources/characters.png")
    map["enemies"] = loadImage( "src/main/resources/enemies.png")
    map["foliage"] = loadImage( "src/main/resources/foliage.png")
    map["trees"] = loadImage( "src/main/resources/trees.png")
    map["UIOne"] = loadImage( "src/main/resources/UIOne.png")

    return map
}
