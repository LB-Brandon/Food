package com.brandon.food.mainRecipeDetail.api


data class DetailResponse(
    val result: Result
)

data class Result(
    val haveIngredient: List<String>,
    val missingIngredient: List<String>,
)
