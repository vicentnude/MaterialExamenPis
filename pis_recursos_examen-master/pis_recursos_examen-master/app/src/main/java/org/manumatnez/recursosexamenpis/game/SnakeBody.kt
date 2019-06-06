package org.manumatnez.recursosexamenpis.game

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import org.manumatnez.recursosexamenpis.*

class SnakeBody(private var image: Bitmap, snakeW : Int, snakeH : Int, posX: Int, posY: Int) : GameChar {

    var x = posX
    var y = posY

    private var w: Int = snakeW
    private var h: Int = snakeH

    fun getW() : Int {
        return w
    }

    fun getH() : Int {
        return h
    }

    private val halfW: Int = w / 2
    private val halfH: Int = h / 2

    var rect: Rect = Rect()

    override fun update() {
        if (x > widthGame) {
            x = 0
        } else if (x <= 0) {
            x = widthGame
        }

        if (y > heightGame) {
            y = 0
        } else if (y <= 0) {
            y = heightGame
        }

        when(movement) {
            UP -> {
                this.y -= snakeSpeed
            }
            DOWN -> {
                this.y += snakeSpeed

            }
            LEFT -> {
                this.x -= snakeSpeed

            }
            RIGHT -> {
                this.x += snakeSpeed
            }
        }
    }

    override fun draw(canvas: Canvas) {
        rect.set(x - halfW, y - halfH, x + halfW, y + halfH)
        canvas.drawBitmap(image, null, rect, null)
    }
}