package com.brandon.food.main.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "main_recipes")
data class MainRecipe(
    @PrimaryKey
    @SerializedName("_id")
    val id: String,
    val name: String?,
    val serving: Int?,
    val time: Int?,
    val ingredients: List<String>?,
    val instructions: List<String>?,
    val imageUrl: String?,
) : java.io.Serializable
