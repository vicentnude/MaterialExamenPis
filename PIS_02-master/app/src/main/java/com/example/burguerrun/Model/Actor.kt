package com.example.burguerrun.Model

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.burguerrun.Singletons

open abstract class Actor(number : Int,currentTile:LevelTile) {

    lateinit var image:Bitmap
    var number = number// number to check
    var currentTile = currentTile//current tile of matrix
    var x = currentTile.pixelX//current pixels
    var y = currentTile.pixelY

    fun draw(canvas: Canvas) {//we draw thea actor at current pixels
        canvas.drawBitmap(image, x!!.toFloat(), y!!.toFloat(), null)
    }

    fun colission(ac : Actor):Boolean{//checks if 2 actors collides
        return this.currentTile.equals(ac.currentTile)//same tile == collide
    }

    fun DptoPx(dp:Int):Int{//conversion from dp to px
        return Math.round(dp* Singletons.scale!!)
    }


    abstract fun update()//where's the actor gonna be the next frame?
    //movements
    abstract fun moveUp()
    abstract fun moveDown()
    abstract fun moveRight()
    abstract fun moveLeft()

}