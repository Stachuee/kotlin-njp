

import Canvas.Canvas
import GameManagers.*
import GameRenderer.GameCamera

import InputSystem.InputController
import SimulationEngine.Time
import GameObject.Units.UnitController
import Utils.MathUtils

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage
import org.openrndr.extra.noise.Random
import org.openrndr.math.Vector2


var Assets : Map<String, ColorBuffer> = mapOf<String, ColorBuffer>()

fun main() = application {
    configure {
        width = 800
        height = 800


    }
    program {
        Assets = loadAssets()
        Random.seed = System.currentTimeMillis().toString()

        extend {
            drawer.clear(ColorRGBa.BLACK)
        }

        MathUtils.rotateTo(Vector2.ONE, Vector2(1.0, 0.0))

        extend(UnitController)
        extend(Time)
        extend(InputController)
        extend(UpdateManager)
        extend(Canvas)
        extend(GameCamera)

        UpdateManager.addListener(BuildingManager)
        UpdateManager.addListener(ResourceManager)
        UpdateManager.addListener(VillageController)
        UpdateManager.addListener(WaveSpawner)

        /*
        val mat = Material(Vector2(0.0,0.0), "buildings")
        Canvas.instantiateCanvasObject(
            BuildButton(ObjectRenderer(mat, 0), Vector2(0.0, 0.0), Vector2(160.0, 160.0))
        )
         */
        InputController.addMouseListener(BuildingManager)

        MapController.generateMap(Vector2(5000.0, 5000.0))
    }
}

fun loadAssets(): Map<String, ColorBuffer> {
    val map = HashMap<String, ColorBuffer>()

    map["buildings"] = loadImage( "data/images/buildings.png")
    map["characters"] = loadImage( "data/images/characters.png")
    map["enemies"] = loadImage( "data/images/enemies.png")
    map["foliage"] = loadImage( "data/images/foliage.png")
    map["trees"] = loadImage( "data/images/trees.png")
    map["UIOne"] = loadImage( "data/images/UIOne.png")

    return map
}
