package com.brandon.food.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.apiservice.FoodIApiService
import com.brandon.food.inventory.api.DeleteIngredientRequest
import com.brandon.food.inventory.model.InventoryIngredient
import com.brandon.food.main.User
import kotlinx.coroutines.launch

class InventoryViewModel(private val apiService: FoodIApiService) : ViewModel() {
    private val TAG = "InventoryViewModel"
    private val _inventoryIngredients = MutableLiveData<List<InventoryIngredient>>()
    val inventoryIngredients: LiveData<List<InventoryIngredient>> = _inventoryIngredients

    init {
        Log.i(TAG, "init: ")
        fetchInventoryIngredients(User.email)
    }

    fun fetchInventoryIngredients(email: String) {
        Log.i(TAG, "fetchInventoryIngredients: $email")
        viewModelScope.launch {
            try {
                val response = FoodApi.service.getIngredients(email)
                if (response.isSuccessful && response.body() != null) {
                    val inventoryIngredients = response.body()!!.result
                    for (inventoryIngredient in inventoryIngredients) {
                        Log.d(TAG, "InventoryIngredient: ${inventoryIngredient.name}")
                    }
                    updateItemList(inventoryIngredients)
                } else {
                    Log.d(TAG, "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteInventoryIngredient(item: InventoryIngredient) {
        viewModelScope.launch {
            try {
                val deleteIngredientRequest = DeleteIngredientRequest(User.email, item.name)
                val response = FoodApi.service.deleteIngredient(deleteIngredientRequest)
                if(response.isSuccessful && response.body() != null){
                    val result = response.body()!!
                    Log.i(TAG, "deleteInventoryIngredient: ${result.message}")
                    Log.i(TAG, "deleteInventoryIngredient: ${result.status}")
                    if (result.message == "success") {
                        val updatedList = inventoryIngredients.value!!.toMutableList()
                        updatedList.remove(item)
                        _inventoryIngredients.postValue(updatedList)

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getItemListLiveData(): LiveData<List<InventoryIngredient>> {
        return inventoryIngredients
    }

    fun updateItemList(itemList: List<InventoryIngredient>) {
        _inventoryIngredients.value = itemList
    }
}