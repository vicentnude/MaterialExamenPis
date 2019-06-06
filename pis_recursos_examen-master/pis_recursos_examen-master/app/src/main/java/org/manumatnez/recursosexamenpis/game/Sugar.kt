package org.manumatnez.recursosexamenpis.game

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import org.manumatnez.recursosexamenpis.*

const val MAX_DELAY = 50
var sugar_speed = 15

class Sugar (imageSugar : Bitmap, sugarW : Int, sugarH : Int, posX: Int, posY: Int, var points : Long = 1, private var canMove : Boolean = false) : GameChar {

    var imgSugar = imageSugar

    var x: Int = posX
    var y: Int = posY

    var w: Int = sugarW
    var h: Int = sugarH

    private val halfW: Int = w / 2
    private val halfH: Int = h / 2

    var rect: Rect = Rect()

    var delay : Int = 0
    var direction : Int = UP

    override fun update() {
        if (canMove) {
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

            if (delay > MAX_DELAY) {
                direction = (UP..RIGHT).shuffled().first()
                delay = 0
            }

            when(direction) {
                UP -> {
                    this.y -= sugar_speed
                }
                DOWN -> {
                    this.y += sugar_speed

                }
                LEFT -> {
                    this.x -= sugar_speed

                }
                RIGHT -> {
                    this.x += sugar_speed
                }
            }
            delay++
        }
    }

    override fun draw(canvas: Canvas) {
        rect.set(x - halfW, y - halfH, x + halfW, y + halfH)
        canvas.drawBitmap(imgSugar, null, rect, null)
    }

    fun collision(char: GameChar) : Boolean {
        if (char is Sugar && this.rect.intersect(char.rect)) {
            return true
        }

        if (char is SnakeBody && this.rect.intersect(char.rect)) {
            return true
        }
        return false
    }

}