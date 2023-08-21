package com.brandon.food.singup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.singup.api.SignUpRequest
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> = _signUpSuccess

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val request = SignUpRequest(email, password, name)
                val response = FoodApi.service.signUpUser(request)
                if (response.isSuccessful && response.body() != null) {
                    val signUpStatus = response.body()!!.status
                    val message = response.body()!!.message
                    if (signUpStatus) {
                        // show success message
                        Log.i("SignUpViewModel", message)
                        _signUpSuccess.value = true
                    } else {
                        // show error message
                        Log.i("SignUpViewModel", message)
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
