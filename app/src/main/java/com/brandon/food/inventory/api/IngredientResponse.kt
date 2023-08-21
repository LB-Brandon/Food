package com.brandon.food.inventory.api

import com.brandon.food.inventory.model.InventoryIngredient

data class IngredientResponse(
    val result: List<InventoryIngredient>
)
