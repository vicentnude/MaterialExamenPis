package com.example.burguerrun.Model

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.burguerrun.Singletons

class Objecte(image:Bitmap) {
    var x:Int = 0
    var y:Int = 0
    var image = Bitmap.createScaledBitmap(image,DptoPx(22),DptoPx(25),true)

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }
    fun DptoPx(dp:Int):Int{
        return Math.round(dp* Singletons.scale!!)
    }


}