package com.brandon.food.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brandon.food.apiservice.FoodIApiService

class MainActivityViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
