package com.example.burguerrun.Engine

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import com.example.burguerrun.Model.*
import com.example.burguerrun.R
import com.example.burguerrun.Singletons
import com.example.burguerrun.View.HistoriaNivell
import com.example.burguerrun.View.MenuGuanyat
import com.example.burguerrun.View.MenuLose
import kotlin.math.absoluteValue
import kotlin.collections.HashMap



class GameEngine(context: Context,gameView: GameView) {
    var ctxt=context
    var gV=gameView
    var lvl = Singletons.level
    var lvlMatrix = lvl.viewMatrix
    var plyr = Singletons.plyr
    var enemies = lvl.ingredients
    var num = lvl.num
    val LEVEL = "com.example.burguerrun.level"
    var num_1: Objecte
    var num_2: Objecte
    var pared: Objecte
    var terraCuina: Objecte
    var cor: Objecte
    var porta: Objecte
    var itime:Long = 0
    var ftime:Long = 0
    var vibrate : Int = 0
    lateinit var intent2 : Intent


    init{
        pared = Objecte(BitmapFactory.decodeResource(ctxt.resources, lvl.muro))
        terraCuina = Objecte(BitmapFactory.decodeResource(ctxt.resources, lvl.suelo))
        cor = Objecte(BitmapFactory.decodeResource(ctxt.resources, R.drawable.cor))
        porta =  Objecte(BitmapFactory.decodeResource(ctxt.resources, R.drawable.porta))
        num_1 = Objecte(BitmapFactory.decodeResource(ctxt.resources, lvl.num1))
        num_2 =  Objecte(BitmapFactory.decodeResource(ctxt.resources, lvl.num2))
        itime = System.nanoTime()


    }

    fun move() : Int{
        var m = plyr.mou
        plyr.mou = 0
        return m
    }

    fun draw(canvas: Canvas){
        gV.draw(canvas!!);
        var count = drawMap(canvas)//read matrix and draws based on value
        checkEnd(count)//checks collision with door to finish game and save data to base
        plyr.draw(canvas!!);//drawing player at current location
        checkPlayerEnemiesColission(canvas!!)
    }

    fun goUp(){
        plyr.direction=2;
    }
    fun goDown(){

        plyr.direction=3;
    }
    fun goLeft(){

        plyr.direction=1;
    }
    fun goRight(){

        plyr.direction=0;
    }

    fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /*
    * This method checks if an enemy is collisioning with another enemy.In that case,that ingredients move randomly.
    * Otherwise, call the Individual Ai method.
    **/
    fun allEnemiesAi(){

        var nohanColisionat=true

        for (i in 0..enemies.size-1){
            for (j in 0..enemies.size-1) {
                if(enemies[j].colission(enemies[i]) && !enemies[j].equals(enemies[i])){
                      nohanColisionat=false
                    when ((0..3).random()) {
                        0 -> enemies[j].moveRight()
                        1 -> enemies[j].moveLeft()
                        2 -> enemies[j].moveUp()
                        3 -> enemies[j].moveDown()

                    }
                }
            }
            if(nohanColisionat){
               iaEnemies(enemies[i])
            }else{
                when ((0..3).random()) {
                    0 -> enemies[i].moveRight()
                    1 -> enemies[i].moveLeft()
                    2 -> enemies[i].moveUp()
                    3 -> enemies[i].moveDown()

                }
            }

        }


    }
    /*
    * This method is an AI based on A-Star search algorithm using as heuristic the euclidean distance.
    * Instead of begin with an infinite value(999999999999 for example as max distance is around 10000 ),
    * the algorithm has a default distance of 900(with a random movement assigned) causing that the A star only is used when enemies are near to the player.
    * Otherwise, A star is used and they try to chase the player inside the maze
    *
    * */
    fun iaEnemies(ing:Ingredient){
        var x2:Int=plyr.x
        var y2:Int=plyr.y
        val distances: HashMap<Double?, Int> = hashMapOf( 99999.0 to 0 );

        if (ing.currentTile.right != null && !ing.currentTile.right!!.value.equals("p")){
            var x1:Int?= ing.currentTile.right!!.pixelX
            var y1:Int?= ing.currentTile.right!!.pixelY
            distances[Math.sqrt(((y2 - y1!!) * (y2 - y1) + (x2 - x1!!) * (x2 - x1!!)).absoluteValue.toDouble())]=0;
        }
        if (ing.currentTile.left != null && !ing.currentTile.left!!.value.equals("p")){
            var x1:Int?= ing.currentTile.left!!.pixelX
            var y1:Int?= ing.currentTile.left!!.pixelY
            distances[Math.sqrt(((y2 - y1!!) * (y2 - y1) + (x2 - x1!!) * (x2 - x1!!)).absoluteValue.toDouble())]=1;
        }
        if (ing.currentTile.up != null && !ing.currentTile.up!!.value.equals("p")){
            var x1:Int?= ing.currentTile.up!!.pixelX
            var y1:Int?= ing.currentTile.up!!.pixelY
            distances[Math.sqrt(((y2 - y1!!) * (y2 - y1) + (x2 - x1!!) * (x2 - x1!!)).absoluteValue.toDouble())]=2;
        }
        if (ing.currentTile.down != null && !ing.currentTile.down!!.value.equals("p")){
            var x1:Int?= ing.currentTile.down!!.pixelX
            var y1:Int?= ing.currentTile.down!!.pixelY
            distances[Math.sqrt(((y2 - y1!!) * (y2 - y1) + (x2 - x1!!) * (x2 - x1!!)).absoluteValue.toDouble())]=3;
        }
        var action:Int=(0..3).random();
        var distance:Double=900.0//99999.0;
        for(element in distances.keys){
            if (distance>element!!){
                action=distances.get(element)!!
                distance=element!!

            }

        }
        when (action) {
            0 -> ing.moveRight()
            1 -> ing.moveLeft()
            2 -> ing.moveUp()
            3 -> ing.moveDown()

        }

    }

    fun drawMap(canvas: Canvas):Int{
        var count = 0

        for (x in 0..lvlMatrix.size - 1){
            for(y in 0..lvlMatrix[x].size - 1){
                if (lvlMatrix[x][y].value!!.equals("p")){

                    pared.x = lvlMatrix[x][y].pixelX!!
                    pared.y = lvlMatrix[x][y].pixelY!!
                    pared.draw(canvas!!)
                }
                if (lvlMatrix[x][y].value!!.equals("s")){

                    terraCuina.x = lvlMatrix[x][y].pixelX!!
                    terraCuina.y = lvlMatrix[x][y].pixelY!!
                    terraCuina.draw(canvas!!)
                }
                if (lvlMatrix[x][y].value!!.equals("c")){
                    count++
                    if (count <= plyr.life){

                        cor.x = lvlMatrix[x][y].pixelX!!
                        cor.y = lvlMatrix[x][y].pixelY!!
                        cor.draw(canvas!!)
                    }
                }
                if (lvlMatrix[x][y].value!!.equals("d")){

                    terraCuina.x = lvlMatrix[x][y].pixelX!!
                    terraCuina.y = lvlMatrix[x][y].pixelY!!
                    terraCuina.draw(canvas!!)
                    porta.x = lvlMatrix[x][y].pixelX!!
                    porta.y = lvlMatrix[x][y].pixelY!!
                    porta.draw(canvas!!)
                }
                if (lvlMatrix[x][y].value!!.equals("n")){

                    num_1.x = lvlMatrix[x][y].pixelX!!
                    num_1.y = lvlMatrix[x][y].pixelY!!
                    num_1.draw(canvas!!)
                }
                if (lvlMatrix[x][y].value!!.equals("m")){

                    num_2.x = lvlMatrix[x][y].pixelX!!
                    num_2.y = lvlMatrix[x][y].pixelY!!
                    num_2.draw(canvas!!)
                }
            }
        }
        return count
    }

    fun checkEnd(count:Int){
        if (plyr.currentTile!!.value.equals("d")){
            gV.thread!!.setRunning(false)
            ftime = (System.nanoTime() - itime)/1000000000 //time in seconds
            var newuser:MutableMap<String,Any> = HashMap<String,Any>()
            newuser["level"+lvl.stage.toString()] = true
            val points = 75000/(ftime) + ((3-count) * 250)
            if (Singletons.database.currentUser!!.points[lvl.stage - 1] < points){
                Singletons.database.currentUser!!.points[lvl.stage - 1] = points//point formula
                newuser["point"+lvl.stage.toString()] = points
            }
            Singletons.database.currentUser!!.wonLvl[lvl.stage -1] = true
            if(num == 24){
                intent2 = Intent(ctxt, HistoriaNivell::class.java)
                intent2.putExtra(LEVEL,"Guanyat")
            }
            else{
                intent2 = Intent(ctxt, MenuGuanyat::class.java)
            }
            if (!Singletons.database.currentUser!!.email.equals("")){
                Singletons.db.collection("users").document(Singletons.database.currentUser!!.email).update(newuser)
            }
            ctxt.startActivity(intent2)
            Singletons.lvlPresenter.actv.finish()
            enemies.clear()
            Singletons.level.ingredients.clear()
        }
    }

    fun checkPlayerEnemiesColission(canvas: Canvas){
        var i = 0//random
        var j = 1//random
        for (x in 0..enemies.size-1) {
            if (plyr.colission(enemies[x])) {
                vibrate = 1

                if (num % enemies[x].number == 0){
                    while (lvlMatrix[i][j].value == "p"){
                        i = (3..15).random()
                        j = (3..15).random()
                    }

                    lvlMatrix[i][j].value = "d"
                    enemies.removeAt(x)
                    plyr.skinSelector(3)
                }
                else{

                    plyr.life--
                    plyr.skinSelector(plyr.life)
                    enemies.removeAt(x)
                    if (plyr.life == 0) {
                        gV.thread!!.setRunning(false)
                        val intent2 = Intent(ctxt, MenuLose::class.java)
                        ctxt.startActivity(intent2)
                        Singletons.lvlPresenter.actv.finish()
                        enemies.clear()
                        Singletons.level.ingredients.clear()
                    }
                }

            } else {
                enemies[x].draw(canvas!!)
            }
        }
    }


}
