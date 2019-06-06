package com.example.burguerrun.Model


open abstract class Ingredient(currentTile: LevelTile, number : Int):  Actor(number, currentTile) {

    override fun update() {//utilizar para la IA EN CASO DE NECESARIO
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    abstract fun selectImage(number: Int)

    override fun moveUp(){
        if (currentTile.up != null && !currentTile.up!!.value.equals("p")) {
            y = currentTile.up!!.pixelY
            currentTile = currentTile.up!!
        }
        else{
            when((0..2).random()) {
                0 -> moveRight()
                1-> moveLeft()
                else ->  moveLeft()
            }

        }
    }
    override fun moveDown(){
        if (currentTile.down != null && !currentTile.down!!.value.equals("p")) {
            y = currentTile.down!!.pixelY
            currentTile = currentTile.down!!
        }
        else{
            when((0..2).random()) {
                0 -> moveRight()
                1-> moveLeft()
                else ->  moveLeft()
            }

        }
    }
    override fun moveLeft(){
        if (currentTile.left != null && !currentTile.left!!.value.equals("p")) {
            x = currentTile.left!!.pixelX
            currentTile = currentTile.left!!//actualizo la tile ya que confirmamos que hemos cambiado de tile
        }
        else{
            when((0..2).random()) {
                0 -> moveDown()
                1-> moveUp()
                else ->  moveDown()
            }

        }
    }
    override fun moveRight(){
        if (currentTile.right != null && !currentTile.right!!.value.equals("p")){
            x = currentTile.right!!.pixelX
            currentTile = currentTile.right!!

        }
        else{
            when((0..2).random()) {
                0 -> moveDown()
                1-> moveUp()
                else ->  moveDown()
            }

        }
    }

}