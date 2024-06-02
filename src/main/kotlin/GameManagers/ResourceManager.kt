package GameManagers

import Canvas.Canvas
import Canvas.Icon
import Canvas.Text
import GameRenderer.Material
import GameRenderer.ObjectRenderer
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2

object ResourceManager : IUpdate {
    var gold : Int = 0
    var food : Int = 0


    val mat = Material(Vector2(0.0,0.0), "UIOne")
    val meatMat = Material(Vector2(0.0,1.0), "UIOne")
    val goldMat = Material(Vector2(0.0,0.0), "UIOne")

    val goldText : Text = Text(ObjectRenderer(mat, 0), Vector2(60.0, 40.0), Vector2(0.0, 0.0))
    val meatText : Text = Text(ObjectRenderer(mat, 0), Vector2(160.0, 40.0), Vector2(0.0, 0.0))

    val meatIcon = Icon(ObjectRenderer(meatMat, 0), Vector2(125.0, 25.0), Vector2(25.0, 25.0))
    val goldIcon = Icon(ObjectRenderer(goldMat, 0), Vector2(25.0, 25.0), Vector2(25.0, 25.0))

    init {
        gold = 15
        food = 5


        Canvas.instantiateCanvasObject(meatIcon)
        Canvas.instantiateCanvasObject(goldIcon)

        Canvas.instantiateCanvasObject(goldText)
        Canvas.instantiateCanvasObject(meatText)
    }

    override fun update() {
        goldText.setText(gold.toString())
        meatText.setText(food.toString())
    }

    fun addResources(gold: Int, food: Int){
        this.gold += gold
        this.food += food
    }

    fun removeResources(gold: Int, food: Int){
        this.gold -= gold
        this.food -= food
    }
    fun removeResources(resources : IntVector2){
        this.gold -= resources.x
        this.food -= resources.y
    }

    fun canAfford(gold: Int, food: Int) : Boolean {
        return this.gold >= gold && this.food >= food
    }

    fun canAfford(resources : IntVector2) : Boolean {
        return this.gold >= resources.x && this.food >= resources.y
    }

}