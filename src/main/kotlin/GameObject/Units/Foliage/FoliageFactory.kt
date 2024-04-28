package GameObject.Units.Foliage

import GameRenderer.ObjectRenderer
import org.openrndr.extra.noise.Random
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2

object FoliageFactory {

    val foliageAtlasSize = IntVector2(6,4)


    fun buildFoliage(biom: FoliageBiom, type: FoliageType) : Foliage{
        val atlas = getAtlasPosition(biom, type)
        val renderer = ObjectRenderer("foliage", atlas, 0)
        return Foliage(renderer)
    }

    private fun getAtlasPosition(biom: FoliageBiom, type: FoliageType) : Vector2{
        var atlas = when(biom){
            FoliageBiom.SHORE -> Vector2(0.0,0.0)
            FoliageBiom.LIGHT_FORREST -> Vector2(0.0,2.0)
            FoliageBiom.BADLANDS -> Vector2(2.0,0.0)
            FoliageBiom.SWAMP -> Vector2(2.0,2.0)
            FoliageBiom.DARK_FORREST -> Vector2(4.0,0.0)
            FoliageBiom.DARK_BADLANDS -> Vector2(4.0,2.0)
        }


        atlas += when(type){
            FoliageType.TREE -> {
                if(Random.double(0.0,1.0) < 0.5) Vector2(0.0,0.0)
                else Vector2(0.0,1.0)
            }
            FoliageType.BUSH -> Vector2(1.0,0.0)
            FoliageType.VANITY -> Vector2(1.0,1.0)
        }

        return atlas
    }
}