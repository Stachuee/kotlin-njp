package GameManagers

import GameObject.Units.Foliage.Foliage
import GameObject.Units.Foliage.FoliageBiom
import GameObject.Units.Foliage.FoliageFactory
import GameObject.Units.Foliage.FoliageType
import org.openrndr.extra.noise.Random
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2

object Map {
    var mapSize : Vector2 = Vector2(0.0, 0.0)
    var foliageList : MutableList<Foliage> = mutableListOf()

    val nodeDistance = 160
    val seedStrength = 1.0
    val seedStrengthDrop = 0.1


    var mapNodeBioms = Array(2) { Array<FoliageBiom>(2) { FoliageBiom.LIGHT_FORREST } }
    var mapNodeSize : IntVector2 = IntVector2.ZERO

    fun generateMap(mapSize : Vector2)
    {
        this.mapSize = mapSize
        generateBioms()
        generateFoliage(100)
    }

    fun generateBioms(){
        mapNodeSize = IntVector2(kotlin.math.ceil((mapSize.x/nodeDistance)).toInt(), kotlin.math.ceil((mapSize.y/nodeDistance)).toInt())
        mapNodeBioms = Array(mapNodeSize.x){
            Array<FoliageBiom>(mapNodeSize.y) {
                FoliageBiom.LIGHT_FORREST }
        }
        for (i in 1..5)
        {
            enumValues<FoliageBiom>().forEach {
                scatterSeed(it)
            }
        }

        /*
        for(x in mapNodeBioms) {
            for (y in x)
            {
                when(y)
                {
                    FoliageBiom.SHORE -> print("s")
                    FoliageBiom.LIGHT_FORREST ->  print("l")
                    FoliageBiom.BADLANDS ->  print("b")
                    FoliageBiom.SWAMP ->  print("w")
                    FoliageBiom.DARK_BADLANDS ->  print("d")
                    FoliageBiom.DARK_FORREST ->  print("f")
                }
            }
            println()
        }
        */
    }

    fun scatterSeed(biom: FoliageBiom)
    {
        val seedPosition = IntVector2(Random.int(0, mapNodeSize.x), Random.int(0, mapNodeSize.y))
        growSeed(seedPosition, biom, seedStrength, seedStrengthDrop)
    }

    fun growSeed(seedPosition : IntVector2, seedType : FoliageBiom, seedStrength : Double, seedStrengthDrop : Double)
    {
        mapNodeBioms[seedPosition.x][seedPosition.y] = seedType
        if(Random.double(0.0,1.0) > seedStrength) return

        if(seedPosition.x > 0 && mapNodeBioms[seedPosition.x - 1][seedPosition.y] != seedType)
            growSeed(IntVector2(seedPosition.x - 1, seedPosition.y), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.x < mapNodeSize.x - 1 && mapNodeBioms[seedPosition.x + 1][seedPosition.y] != seedType)
            growSeed(IntVector2(seedPosition.x + 1, seedPosition.y), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.y > 0 && mapNodeBioms[seedPosition.x][seedPosition.y - 1] != seedType)
            growSeed(IntVector2(seedPosition.x, seedPosition.y - 1), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

        if(seedPosition.y < mapNodeSize.y - 1 && mapNodeBioms[seedPosition.x ][seedPosition.y + 1] != seedType)
            growSeed(IntVector2(seedPosition.x, seedPosition.y + 1), seedType, seedStrength - seedStrengthDrop, seedStrengthDrop)

    }

    fun generateFoliage(count : Int)
    {
        for (i in count downTo 0 step 1)
        {
            val position = Vector2(Random.double(-mapSize.x/2, mapSize.x/2), Random.double(-mapSize.y/2, mapSize.y/2))

            val toAdd = FoliageFactory.buildFoliage(getBiomFromPosition(position), FoliageType.TREE)
            toAdd.setWorldPosition(position)
            foliageList.add(toAdd)
        }
    }

    fun getBiomFromPosition(postion : Vector2) : FoliageBiom
    {
        val nodePosition = IntVector2(kotlin.math.round(postion.x / 160.0).toInt(), kotlin.math.round(postion.y / 160.0).toInt())
        return mapNodeBioms[nodePosition.x + mapNodeSize.x/2][nodePosition.y+ mapNodeSize.y/2]
    }


}