package com.example.burguerrun.Model

interface Creator {
    fun createIngredient(type:String ,x : Int, y : Int, number : Int): Ingredient
}