package GameManagers

import GameObject.Units.Buildings.BuildingEnum
import GameObject.Units.Foliage.BiomsStructures
import GameObject.Units.Foliage.Foliage
import GameObject.Units.Foliage.FoliageBiom
import GameObject.Units.Foliage.FoliageFactory
import GameObject.Units.Heroes.HeroesBuilder
import GameObject.Units.Heroes.HeroesEnum
import GameObject.Units.UnitController
import GameRenderer.GameCamera
import GameRenderer.ObjectRenderer
import InputSystem.IMousePosition
import Utils.RandomUtils
import org.openrndr.extra.noise.Random
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2

object MapController {
    var mapSize : Vector2 = Vector2(0.0, 0.0)
    var foliageList : MutableList<Foliage> = mutableListOf()

    val nodeDistance = 160
    val seedStrength = 1.0
    val seedBiomStrengthDrop = 0.1
    val seedStructureStrengthDrop = 0.1


    var mapNodeBioms = Array(2) { Array<FoliageBiom>(2) { FoliageBiom.PLAINS } }
    var mapNodeStructures = Array(2) { Array<BiomsStructures>(2) { BiomsStructures.NONE } }
    var mapNodeSize : IntVector2 = IntVector2.ZERO

    //<editor-fold desc="Map gen">

    fun generateMap(mapSize : Vector2)
    {
        this.mapSize = mapSize
        generateBioms()
        generateBiomFoliage(0.00005)
        generateBasicStructures()
        generateStartingUnits()
    }

    fun generateStartingUnits(){
        for(i in 1..1) UnitController.addUnit(HeroesBuilder.placeHero(HeroesEnum.PEASANT, RandomUtils.getPointOnCircle(100.0) ))
    }

    fun generateBasicStructures(){
        BuildingManager.buildBuilding(BuildingEnum.TAVERN, Vector2.ZERO, false)
    }

    fun generateBioms(){
        mapNodeSize = IntVector2(kotlin.math.ceil((mapSize.x/nodeDistance)).toInt(), kotlin.math.ceil((mapSize.y/nodeDistance)).toInt())
        mapNodeBioms = Array(mapNodeSize.x){
            Array<FoliageBiom>(mapNodeSize.y) {
                FoliageBiom.PLAINS }
        }
        mapNodeStructures = Array(mapNodeSize.x){
            Array<BiomsStructures>(mapNodeSize.y) {
                BiomsStructures.NONE }
        }


        for (i in 1..5)
        {
            enumValues<FoliageBiom>().forEach {
                    scatterBiomSeed(it)
            }
        }

        for(i in 1..3)
        {
            scatterStructureSeed(BiomsStructures.FORREST)
        }

        /*
        for(x in mapNodeBioms)
        {
            for (y in x)
            {
                when(y)
                {
                    FoliageBiom.FORREST -> print("F")
                    FoliageBiom.PLAINS -> print("P")
                    FoliageBiom.BADLANDS -> print("B")
                }

            }
            println()
        }
        */
    }

    fun scatterStructureSeed(structure : BiomsStructures)
    {
        val seedPosition = IntVector2(Random.int(0, mapNodeSize.x), Random.int(0, mapNodeSize.y))

        growStructureSeed(seedPosition, structure, seedStrength, seedStructureStrengthDrop)
    }

    fun growStructureSeed(seedPosition : IntVector2, seedType : BiomsStructures, seedStrength : Double, seedStrengthDrop : Double)
    {
        mapNodeStructures[seedPosition.x][seedPosition.y] = seedType
        if(Random.double(0.0,1.0) > seedStrength) return

        if(seedPosition.x > 0 && mapNodeStructures[seedPosition.x - 1][seedPosition.y] != seedType)
            growStructureSeed(IntVector2(seedPosition.x - 1, seedPosition.y), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.x < mapNodeSize.x - 1 && mapNodeStructures[seedPosition.x + 1][seedPosition.y] != seedType)
            growStructureSeed(IntVector2(seedPosition.x + 1, seedPosition.y), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.y > 0 && mapNodeStructures[seedPosition.x][seedPosition.y - 1] != seedType)
            growStructureSeed(IntVector2(seedPosition.x, seedPosition.y - 1), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.y < mapNodeSize.y - 1 && mapNodeStructures[seedPosition.x ][seedPosition.y + 1] != seedType)
            growStructureSeed(IntVector2(seedPosition.x, seedPosition.y + 1), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)
    }

    fun scatterBiomSeed(biom: FoliageBiom)
    {
        val seedPosition = IntVector2(Random.int(0, mapNodeSize.x), Random.int(0, mapNodeSize.y))

        growBiomSeed(seedPosition, biom, seedStrength, seedBiomStrengthDrop)
    }

    fun growBiomSeed(seedPosition : IntVector2, seedType : FoliageBiom, seedStrength : Double, seedStrengthDrop : Double)
    {
        mapNodeBioms[seedPosition.x][seedPosition.y] = seedType
        if(Random.double(0.0,1.0) > seedStrength) return

        if(seedPosition.x > 0 && mapNodeBioms[seedPosition.x - 1][seedPosition.y] != seedType)
            growBiomSeed(IntVector2(seedPosition.x - 1, seedPosition.y), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.x < mapNodeSize.x - 1 && mapNodeBioms[seedPosition.x + 1][seedPosition.y] != seedType)
            growBiomSeed(IntVector2(seedPosition.x + 1, seedPosition.y), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.y > 0 && mapNodeBioms[seedPosition.x][seedPosition.y - 1] != seedType)
            growBiomSeed(IntVector2(seedPosition.x, seedPosition.y - 1), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.y < mapNodeSize.y - 1 && mapNodeBioms[seedPosition.x ][seedPosition.y + 1] != seedType)
            growBiomSeed(IntVector2(seedPosition.x, seedPosition.y + 1), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

    }

    fun generateBiomFoliage(densityPerPixel : Double)
    {
        for (i in (mapSize.x * mapSize.y * densityPerPixel).toInt() downTo 0 step 1)
        {
            val position = Vector2(Random.double(-mapSize.x/2, mapSize.x/2), Random.double(-mapSize.y/2, mapSize.y/2))
            var treeChance = 0.0
            var foliageChance = 0.0
            when(getStructureFromPosition(position))
            {
                BiomsStructures.NONE -> {
                    treeChance = 0.03
                    foliageChance = 0.02
                }
                BiomsStructures.FORREST -> {
                    treeChance = 0.8
                    foliageChance = 0.2
                }
            }

            if(Random.double(0.0, 1.0) < treeChance) {
                val toAdd = FoliageFactory.buildTrees(getBiomFromPosition(position), -position.y.toInt())
                toAdd.setWorldPosition(position)
                foliageList.add(toAdd)
            }
            else if(Random.double(0.0, 1.0) < foliageChance)
            {
                val toAdd = FoliageFactory.buildFoliage(getBiomFromPosition(position), -position.y.toInt())
                toAdd.setWorldPosition(position)
                foliageList.add(toAdd)
            }
        }
    }
    //</editor-fold>

    fun getBiomFromPosition(postion : Vector2) : FoliageBiom
    {
        val nodePosition = IntVector2(kotlin.math.floor(postion.x / 160.0).toInt(), kotlin.math.floor(postion.y / 160.0).toInt())
        return mapNodeBioms[nodePosition.x + mapNodeSize.x/2][nodePosition.y+ mapNodeSize.y/2]
    }

    fun getStructureFromPosition(postion : Vector2) : BiomsStructures
    {
        val nodePosition = IntVector2(kotlin.math.floor(postion.x / 160.0).toInt(), kotlin.math.floor(postion.y / 160.0).toInt())
        return mapNodeStructures[nodePosition.x + mapNodeSize.x/2][nodePosition.y+ mapNodeSize.y/2]
    }


    fun getClosestFoliage(position: Vector2) : Foliage?{
        var closest : Foliage? = null
        var closestDist = Double.POSITIVE_INFINITY

        for (x in foliageList){
            if(!x.getActive()) continue
            val dist = (position - x.getWorldPosition()).length

            if(closestDist > dist ){
                closestDist = dist
                closest = x
            }
        }
        return closest
    }
}