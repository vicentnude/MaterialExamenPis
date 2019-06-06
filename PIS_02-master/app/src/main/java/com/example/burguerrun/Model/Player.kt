package com.example.burguerrun.Model


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import com.example.burguerrun.R
import com.example.burguerrun.Singletons

class Player(currentTile: LevelTile, number : Int) : Actor(number, currentTile) {
    var life: Int = 3//life points
    var direction: Int = 0
    var mou: Int = 0
    var limit = false
    var last = ""
    var skinLevelNumber=0;

    init {
        var pxx = DptoPx(22)
        var pxy = DptoPx(25)
        var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger)
        image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
    }

    override fun update() {
        when (this.direction) {
            0 -> moveRight()
            1 -> moveLeft()
            2 -> moveUp()
            3 -> moveDown()
            else -> moveDown()
        }


    }

    override fun moveUp() {
        if (currentTile.up != null && !currentTile.up!!.value.equals("p")) {
            mou = 1
            y = currentTile.up!!.pixelY
            currentTile = currentTile.up!!
        }

    }

    override fun moveDown() {
        if (currentTile.down != null && !currentTile.down!!.value.equals("p")) {
            mou = 1
            y = currentTile.down!!.pixelY
            currentTile = currentTile.down!!
        }
    }

    override fun moveLeft() {
        if (currentTile.left != null && !currentTile.left!!.value.equals("p")) {
            mou = 1
            x = currentTile.left!!.pixelX
            currentTile = currentTile.left!!
        }
    }

    override fun moveRight() {
        if (currentTile.right != null && !currentTile.right!!.value.equals("p")) {
            mou = 1
            x = currentTile.right!!.pixelX
            currentTile = currentTile.right!!
        }

    }

    fun skinSelector(s:Int ){
        //when yo choose for first time the skin: 1,2,3 or 4 you also set the level you are, so when you  call this funcion with 1,2,3 or 4 inside a level
        //the input + skinLevelNumber determines the level:
        //EXAMPLE:
        //skinSelector(2) [in level creation] -> skinLevelNumber=20
        //skinSelector(2) [in level colision,event] -> skinLevelNumber=22
        val sAndSkinLevel:Int=skinLevelNumber+s; //s has the mood and skinlevel, the ingredient
        when(sAndSkinLevel){
            0->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            1->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger1)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
                skinLevelNumber=10;
            }
            11->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger11)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            12->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger12)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            13->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger13)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            2->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger2)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
                skinLevelNumber=20;
            }
            21->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger21)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            22->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger22)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            23->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger23)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            3->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger3)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
                skinLevelNumber=30;
            }
            31->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger31)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            32->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger32)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            33->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger33)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            4->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger4)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
                skinLevelNumber=40;
            }
            41->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger41)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            42->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger42)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }
            43->{
                var pxx = DptoPx(22)
                var pxy = DptoPx(25)
                var tmp = BitmapFactory.decodeResource(Singletons.historiaPresenter.v.context.resources, R.drawable.burger43)
                image = Bitmap.createScaledBitmap(tmp, pxx, pxy, true)//Resized image bitmap
            }


        }

    }
}
