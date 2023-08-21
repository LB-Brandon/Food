package com.brandon.food.mainRecipeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainRecipeDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainRecipeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainRecipeDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}