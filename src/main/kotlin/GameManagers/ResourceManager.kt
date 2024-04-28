package GameManagers

import Canvas.Canvas
import Canvas.Text
import GameRenderer.Material
import GameRenderer.ObjectRenderer
import org.openrndr.math.Vector2

object ResourceManager : IUpdate {
    private var gold : Int = 0
    private var meat : Int = 0

    fun getGold() = gold
    fun getMeat() = meat

    fun addGold(gold: Int) { this.gold = gold }
    fun addMeat(meat: Int) { this.meat = meat }

    init {
        val mat = Material(Vector2(0.0,0.0), "buildings")
        val text = Text(ObjectRenderer(mat, 0), Vector2(100.0, 100.0), Vector2(0.0, 0.0))
        text.setText("moni")
        Canvas.instantiateCanvasObject(text)
    }

    override fun update() {

    }

}