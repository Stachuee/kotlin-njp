package GameObject.Units.Foliage

import GameRenderer.ObjectRenderer
import org.openrndr.extra.noise.Random
import org.openrndr.extra.noise.random
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2

object FoliageFactory {

    val foliageAtlasSize = IntVector2(6,4)


    fun buildFoliage(biom: FoliageBiom) : Foliage{
        val atlas = getAtlasPosition(biom)
        val renderer = ObjectRenderer("foliage", atlas, 0)
        return Foliage(renderer)
    }

    fun buildFoliage(biom: FoliageBiom, sortingLayer : Int) : Foliage{
        val atlas = getAtlasPosition(biom)
        val renderer = ObjectRenderer("foliage", atlas, sortingLayer)
        return Foliage(renderer)
    }

    fun buildTrees(biom: FoliageBiom, sortingLayer : Int) : Foliage{
        val atlas = getAtlasPosition(biom)
        val renderer = ObjectRenderer("trees", atlas, sortingLayer)
        return Foliage(renderer)
    }

    private fun getAtlasPosition(biom: FoliageBiom) : Vector2{
        var atlas = Vector2(Random.int(0,3).toDouble())

        atlas += when(biom)
        {
            FoliageBiom.FORREST -> Vector2(0.0,0.0)
            FoliageBiom.PLAINS -> Vector2(1.0,0.0)
            FoliageBiom.BADLANDS -> Vector2(2.0,0.0)
        }

        return atlas
    }
}