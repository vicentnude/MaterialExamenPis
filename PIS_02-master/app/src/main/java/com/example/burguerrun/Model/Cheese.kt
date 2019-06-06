package com.example.burguerrun.Model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.burguerrun.R
import com.example.burguerrun.Singletons

class Cheese (currentTile: LevelTile, number : Int) : Ingredient(currentTile, number)  {
    lateinit var tmp:Bitmap

    override fun selectImage(number: Int){
        when(number){
            3-> {
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.formatge_3)
                image = Bitmap.createScaledBitmap(tmp,DptoPx(22),DptoPx(25),true)//Resized image bitmap
            }
            4->{
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.formatge_4)
                image = Bitmap.createScaledBitmap(tmp,DptoPx(22),DptoPx(25),true)//Resized image bitmap
            }
            5->{
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.formatge_5)
                image = Bitmap.createScaledBitmap(tmp,DptoPx(22),DptoPx(25),true)//Resized image bitmap
            }
            7->{
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.formatge_7)
                image = Bitmap.createScaledBitmap(tmp,DptoPx(22),DptoPx(25),true)//Resized image bitmap
            }
            10->{
                tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.formatge_10)
                image = Bitmap.createScaledBitmap(tmp,DptoPx(22),DptoPx(25),true)//Resized image bitmap
            }
        }
    }
}