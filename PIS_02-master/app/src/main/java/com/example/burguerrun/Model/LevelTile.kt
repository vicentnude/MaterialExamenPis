package com.example.burguerrun.Model

import com.example.burguerrun.Singletons

class LevelTile() {
    var x:Int? = null
    var y:Int? = null
    var value:String? = null
    var pixelX:Int = 0
    var pixelY:Int = 0
    var pminX:Int = 0
    var pminY:Int = 0
    var pmaxX:Int = 0
    var pmaxY:Int = 0
    var up:LevelTile? = null
    var down:LevelTile? = null
    var left:LevelTile? = null
    var right:LevelTile? = null

    fun createTile(x:Int,y:Int,value:String){
        this.x = x
        this.y = y
        this.value = value
        pixelX = DptoPx((x + 0.5f)*22f)//0.5f para coger la mitad * los dp que mide una tile
        pixelY = DptoPx((y + 0.5f)*25f)//luego se aplica conversion de dp a pixel
        pminX = DptoPx((x)*22f)
        pmaxX = DptoPx((x+1)*22f)
        pminY = DptoPx((y)*25f)
        pmaxY = DptoPx((y+1)*25f)

    }

    fun DptoPx(dp:Float):Int{
        val scale = Singletons.scale!!
        val result = Math.round(dp*scale)
        return result
    }

    fun setNext(up:LevelTile?,down:LevelTile?,left:LevelTile?,right:LevelTile?){
        this.up = up
        this.right = right
        this.down = down
        this.left = left
    }

    fun checkpointInside(pointx:Int,pointy:Int):Boolean{
        if (pointx <= pmaxX && pointx >= pminX && pointy <= pmaxY && pointy >= pminY){
            return true
        }
        return false
    }
}