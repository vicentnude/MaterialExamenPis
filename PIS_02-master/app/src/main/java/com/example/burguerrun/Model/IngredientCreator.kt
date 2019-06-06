package com.example.burguerrun.Model

import com.example.burguerrun.Singletons

class IngredientCreator(): Creator {

    lateinit var ing:Ingredient

    override fun createIngredient(type: String, x: Int, y: Int, number: Int): Ingredient {
        when(type){
            "cheese"->{
                ing = Cheese(Singletons.level.viewMatrix[x][y],number)
            }

            "tomato"->{
                ing = Tomato(Singletons.level.viewMatrix[x][y],number)
            }

            "lettuce"->{
                ing = Lettuce(Singletons.level.viewMatrix[x][y],number)
            }
        }
        ing.selectImage(number)
        return ing
    }
}