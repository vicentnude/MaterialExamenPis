package org.manumatnez.recursosexamenpis

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import org.manumatnez.recursosexamenpis.game.GameChar
import org.manumatnez.recursosexamenpis.game.IMG_ASSETS
import org.manumatnez.recursosexamenpis.game.Snake
import org.manumatnez.recursosexamenpis.game.Sugar
import java.io.BufferedInputStream
import java.io.File
import kotlin.collections.ArrayList


// SNAKE
const val HEAD = "head.png"
const val BODY = "body.png"

// SUGAR
const val SUGAR = "sugar.png"

// ADD SUGAR
const val SUGAR_TIME = 50
const val MAX_SUGAR = 7

class GameEngine(private var snakeActivity : SnakeActivity) {

    var assets = snakeActivity.applicationContext.assets

    var snake : Snake? = null

    var optionsSnake: BitmapFactory.Options = BitmapFactory.Options()

    var imgHead: Bitmap? = null
    var imgBody: Bitmap? = null
    var imgSugar: Bitmap? = null

    var sugarList : ArrayList<Sugar> = ArrayList()

    var paint : Paint? = null

    var sugarW: Int = (Resources.getSystem().displayMetrics.widthPixels * 0.044905).toInt()
    var sugarH: Int = (Resources.getSystem().displayMetrics.heightPixels * 0.0224525).toInt()

    var snakeHeadW: Int = (Resources.getSystem().displayMetrics.widthPixels * 0.09).toInt()
    var snakeHeadH: Int = (Resources.getSystem().displayMetrics.heightPixels * 0.045).toInt()

    var snakeBodyW: Int = (Resources.getSystem().displayMetrics.widthPixels * 0.07).toInt()
    var snakeBodyH: Int = (Resources.getSystem().displayMetrics.heightPixels * 0.035).toInt()

    var sugarTime : Int = 0

    fun initGame() {
        if (snake == null) {
            sugarTime = 0
            paint = Paint()
            val bgColor = snakeActivity.resources.getColor(R.color.grey, null)
            paint!!.color = bgColor

            optionsSnake.inSampleSize = 2
            imgHead = BitmapFactory.decodeStream(BufferedInputStream(assets.open(IMG_ASSETS + File.separator + HEAD)), null, optionsSnake)!!
            imgBody = BitmapFactory.decodeStream(BufferedInputStream(assets.open(IMG_ASSETS + File.separator + BODY)), null, optionsSnake)!!
            snakeActivity.updatePoints(0)
            snake = Snake(imgHead!!, imgBody!!,snakeHeadW,snakeHeadH,snakeBodyW,snakeBodyH, widthGame / 2, heightGame / 2)

            imgSugar = BitmapFactory.decodeStream(BufferedInputStream(assets.open(IMG_ASSETS + File.separator + SUGAR)), null, optionsSnake)!!
        }
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawRect(0f, 0f, widthGame.toFloat(), heightGame.toFloat(), paint!!)
    }

    fun draw(canvas: Canvas) {
        drawBackground(canvas)

        snake!!.draw(canvas)
        for (s in sugarList) {
            s.draw(canvas)
        }
    }

    fun update() {
        addSugar()
        snake!!.update()
        val delList : ArrayList<Int> = ArrayList()
        for ((i, s) in sugarList.withIndex()) {
            val taken: Boolean = snake!!.takeSugar(s)
            s.update()
            if (taken) {
                delList.add(i)
                snakeActivity.updatePoints(snake!!.getPoints())
            }
        }
        for (i in delList) {
            sugarList.removeAt(i)
        }
    }

    fun addSugar() {
        if (this.sugarTime >= SUGAR_TIME && sugarList.size <= MAX_SUGAR) {
            val sugarTmp: Sugar = generateSugar()
            sugarList.add(sugarTmp)
            this.sugarTime = 0
        }
        this.sugarTime++
    }

    fun generateSugar() : Sugar {
        val randomW = (sugarW..widthGame-sugarW).shuffled().first()
        val randomH = (sugarH..heightGame-sugarH).shuffled().first()
        val move = (0..1).shuffled().first() == 1
        var points = 1
        if (move) {
            points = (2..5).shuffled().first()
        }
        val sugar = Sugar(imgSugar!!, sugarW, sugarH,randomW, randomH, points.toLong(), move)

        val tmpList : ArrayList<GameChar> = ArrayList(sugarList.size+1)
        tmpList.add(snake!!)
        tmpList.addAll(sugarList)

        for (c in tmpList) {
            if (!sugar.collision(c)) {
                return sugar
            }
        }
        return generateSugar()
    }

    fun endGame() {

    }

}