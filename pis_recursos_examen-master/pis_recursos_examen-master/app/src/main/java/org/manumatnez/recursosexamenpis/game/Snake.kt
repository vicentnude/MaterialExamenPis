package org.manumatnez.recursosexamenpis.game

import android.graphics.Bitmap
import android.graphics.Canvas
import org.manumatnez.recursosexamenpis.*

var snakeSpeed = 5

class Snake(private var imgHead : Bitmap, private var imgBody : Bitmap, snakeHeadW : Int, snakeHeadH : Int, var snakeBodyW : Int, var snakeBodyH : Int,
            posX : Int, posY : Int, private var points : Long = 0) : GameChar {

    private val snakeParts: ArrayList<SnakeBody> = ArrayList()

    init {
        snakeSpeed = 10
        snakeParts.add(SnakeBody(imgHead, snakeHeadW, snakeHeadH, posX, posY))
    }

    private fun grow() {
        val last = snakeParts[snakeParts.size-1]
        var posX = last.x
        var posY = last.y

        if (movement == UP) {
            posY += last.getH()
        } else if (movement == DOWN) {
            posY -= last.getH()
        } else if (movement == LEFT) {
            posX += last.getW()
        } else if (movement == RIGHT) {
            posX -= last.getW()
        }

        snakeParts.add(SnakeBody(imgBody, snakeBodyW, snakeBodyH, posX, posY))
    }

    fun getPoints() : Long {
        return points
    }

    override fun update() {
        for (i in 0 until snakeParts.size) {
            snakeParts[i].update()
        }
    }

    override fun draw(canvas: Canvas) {
        for (body in snakeParts) {
            body.draw(canvas)
        }
    }

    fun takeSugar(sugar: Sugar) : Boolean {
        if (snakeParts[0].rect.intersect(sugar.rect)) {
            points += sugar.points
            //grow()
            return true
        }
        return false
    }

}