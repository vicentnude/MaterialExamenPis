package com.example.burguerrun.Model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.burguerrun.R
import com.example.burguerrun.Singletons

class Lettuce(currentTile: LevelTile, number : Int) : Ingredient(currentTile, number) {

    lateinit var tmp: Bitmap

    override fun selectImage(number: Int) {
        when (number) {
            2 -> {
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.lechuga_2)
                image = Bitmap.createScaledBitmap(tmp, DptoPx(22), DptoPx(25), true)//Resized image bitmap
            }
            6 -> {
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.lechuga_6)
                image = Bitmap.createScaledBitmap(tmp, DptoPx(22), DptoPx(25), true)//Resized image bitmap
            }
            7 -> {
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.lechuga_7)
                image = Bitmap.createScaledBitmap(tmp, DptoPx(22), DptoPx(25), true)//Resized image bitmap
            }
            11 -> {
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.lechuga_11)
                image = Bitmap.createScaledBitmap(tmp, DptoPx(22), DptoPx(25), true)//Resized image bitmap
            }
            15 -> {
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.lechuga_15)
                image = Bitmap.createScaledBitmap(tmp, DptoPx(22), DptoPx(25), true)//Resized image bitmap
            }
        }
    }
}