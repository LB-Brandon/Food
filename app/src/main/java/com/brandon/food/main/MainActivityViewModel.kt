package com.brandon.food.main


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.apiservice.FoodIApiService
import com.brandon.food.main.model.MainRecipe
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {
    private val TAG = "MainActivityViewModel"
    private val _mainRecipes = MutableLiveData<List<MainRecipe>>()
    val mainRecipes: LiveData<List<MainRecipe>> = _mainRecipes

    init {
        Log.i(TAG, "init: ")
        fetchMainRecipes(User.email)
    }

    private fun fetchMainRecipes(email: String) {
        viewModelScope.launch {
            try {
                val response = FoodApi.service.getMainRecipes(email)
                if (response.isSuccessful && response.body() != null) {
                    val mainRecipes = response.body()!!.result
                    Log.d(TAG, "Success: ${mainRecipes.size}")
                    for (mainRecipe in mainRecipes) {
                        Log.d(TAG, "MainRecipe: ${mainRecipe.name}")
                        Log.d(TAG, "MainRecipe: ${mainRecipe.imageUrl}")
                    }
                    updateItemList(mainRecipes)
                } else {
                    Log.d(TAG, "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getItemListLiveData(): LiveData<List<MainRecipe>> {
        return mainRecipes
    }

    private fun updateItemList(itemList: List<MainRecipe>) {
        _mainRecipes.value = itemList
    }

    fun getRecipeByPosition(position: Int): MainRecipe {
        Log.i("mainRecipes.value!![position]", mainRecipes.value!![position].toString())
        return mainRecipes.value!![position]
    }
}