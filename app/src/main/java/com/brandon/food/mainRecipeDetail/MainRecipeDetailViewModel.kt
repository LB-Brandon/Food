package com.brandon.food.mainRecipeDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.main.User.email
import com.brandon.food.main.model.MainRecipe
import kotlinx.coroutines.launch

class MainRecipeDetailViewModel() : ViewModel() {
    private val TAG = "MainRecipeDetailViewModel"
    private val _mainRecipe = MutableLiveData<MainRecipe>()
    private val _missingIngredients = MutableLiveData<List<String>>() // 수정된 부분
    private val _haveIngredients = MutableLiveData<List<String>>()

    init {
        Log.i(TAG, "init: ")
    }

    fun fetchIngredientDetail(email: String, name: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = FoodApi.service.seeWhatYouGot(email, name)
                if (response.isSuccessful && response.body() != null) {
                    val ingredientDetail = response.body()!!.result
                    // 정보 저장
                    _missingIngredients.value = ingredientDetail.missingIngredient
                    _haveIngredients.value = ingredientDetail.haveIngredient
                    callback(true)
                } else {
                    Log.d(TAG, "Error: ${response.code()}")
                    callback(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                callback(false)
            }
        }

    }


    fun updateMainRecipe(mainRecipe: MainRecipe) {
        Log.i(TAG, "updateMainRecipe: $mainRecipe")
        _mainRecipe.value = mainRecipe
    }

    fun getMissingIngredients(): List<String>? {
        return _missingIngredients.value
    }

    fun getHaveIngredients(): List<String>? {
        return _haveIngredients.value
    }


}