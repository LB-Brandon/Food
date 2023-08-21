package com.brandon.food.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.signin.api.SignInRequest
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> = _signUpSuccess

    fun singIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val request = SignInRequest(email, password)
                val response = FoodApi.service.signInUser(request)
                if (response.isSuccessful && response.body() != null) {
                    val signInStatus = response.body()!!.status
                    val message = response.body()!!.message
                    if (signInStatus) {
                        Log.i("SignInViewModel", message)
                        _signUpSuccess.value = true
                    } else {
                        Log.i("SignInViewModel", message)
                        _signUpSuccess.value = false
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _signUpSuccess.value = false
            }
        }
    }
}