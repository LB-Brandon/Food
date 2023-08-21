package com.brandon.food.singup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.brandon.food.databinding.ActivitySignUpBinding
import com.brandon.food.main.MainActivity

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    // ViewModel Property Delegation(코틀린 프로퍼티 위임)
    private val viewModel: SignUpViewModel by viewModels { SignUpViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSignUpButton()
    }

    private fun setupSignUpButton() {
        binding.registerBtnCl.setOnClickListener {
            val fullName = binding.userNameEt.text.toString()
            val email = binding.userEmailEt.text.toString()
            val password = binding.userPasswordEt.text.toString()
            val confirmPassword = binding.userConfirmPasswordEt.text.toString()

            if (password != confirmPassword) {
                showErrorMessage("Passwords do not match")
                return@setOnClickListener
            }
            viewModel.signUpSuccess.observe(this) { success ->
                Log.d("SignUpActivity", "Sign up success: $success")
                if (success) {
                    moveToMainActivity()
                } else {
                    showErrorMessage("Sign up failed")
                }
            }

            viewModel.signUp(fullName, email, password)
        }
    }

    private fun moveToMainActivity() {
        Log.i("SignUpActivity", "Sign up success")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


