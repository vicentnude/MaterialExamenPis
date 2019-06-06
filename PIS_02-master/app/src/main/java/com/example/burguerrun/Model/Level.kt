package com.example.burguerrun.Model

import android.graphics.drawable.Drawable
import java.io.File

class Level() {
    var stage:Int = 0
    var num = 0
    var ingredients = ArrayList<Ingredient>(5)
    val max_X = (423/22)+1 //cols
    val max_Y = (732/25)+1 //rows
    var viewMatrix = Array(max_X) { Array(max_Y) { _ -> LevelTile() } }//gameview Dp matrix 30colx20row
    lateinit var startTile:LevelTile

    var muro:Int = 0
    var suelo:Int = 0
    var num1:Int = 0
    var num2:Int = 0
    fun readLvlfromFile(f:File,startRow:Int,startColumn:Int){
        var row = 0
        var col = 0
        f.forEachLine {
            for (c:Char in it){
                if (col < max_Y && row < max_X){
                    viewMatrix[row][col].createTile(row,col,c.toString())
                }
                row++
            }
            col++
            row = 0
        }
        setNexts() //derecha abajo a
        startTile = viewMatrix[startRow][startColumn]
    }

    fun setNexts(){
        for (x in 0..(viewMatrix.size-1)){
            for(y in 0..(viewMatrix[x].size - 1)){
                var up:LevelTile? = null
                var down:LevelTile? = null
                var right:LevelTile? = null
                var left:LevelTile? = null
                if(x == 0){
                    if (y == 0){
                        down = viewMatrix[x][y+1]
                        right = viewMatrix[x+1][y]
                    }
                    else if (y == viewMatrix[x].size - 1){
                        up = viewMatrix[x][y-1]
                        right = viewMatrix[x+1][y]
                    }
                    else{
                        up = viewMatrix[x][y-1]
                        down = viewMatrix[x][y+1]
                        right = viewMatrix[x+1][y]
                    }
                }
                else if(x == viewMatrix.size - 1){
                    if (y == 0){
                        down = viewMatrix[x][y+1]
                        left = viewMatrix[x-1][y]
                    }
                    else if (y == viewMatrix[x].size - 1){
                        up = viewMatrix[x][y-1]
                        left = viewMatrix[x-1][y]
                    }
                    else{
                        up = viewMatrix[x][y-1]
                        left = viewMatrix[x-1][y]
                        down = viewMatrix[x][y+1]
                    }
                }
                else{
                    if (y == 0){
                        down = viewMatrix[x][y+1]
                        down = viewMatrix[x][y+1]
                        right = viewMatrix[x+1][y]
                    }
                    else if (y == viewMatrix[x].size - 1){
                        up = viewMatrix[x][y-1]
                        left = viewMatrix[x-1][y]
                        right = viewMatrix[x+1][y]
                    }
                    else{
                        up = viewMatrix[x][y-1]
                        left = viewMatrix[x-1][y]
                        down = viewMatrix[x][y+1]
                        right = viewMatrix[x+1][y]
                    }

                }
                viewMatrix[x][y].setNext(up,down, left, right)
            }
        }
    }
}
