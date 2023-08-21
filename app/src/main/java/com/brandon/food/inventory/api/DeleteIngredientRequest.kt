package com.brandon.food.inventory.api

data class DeleteIngredientRequest(
    val email : String,
    val ingredient : String,
)
