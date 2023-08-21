package com.brandon.food.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.brandon.food.databinding.ActivitySignInBinding
import com.brandon.food.main.MainActivity

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    private val viewModel: SignInViewModel by viewModels { SignInViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSignInButton()

    }

    private fun setUpSignInButton() {
        binding.loginBtn.setOnClickListener {
            val email = binding.userEmailEt.text.toString()
            val password = binding.userPasswordEt.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showErrorMessage("Please enter email and password")
                return@setOnClickListener
            }

            viewModel.signUpSuccess.observe(this) { success ->
                if (success) {
                    navigateToActivity(MainActivity::class.java)
                } else {
                    showErrorMessage("Sign in failed")
                }
            }

            viewModel.singIn(email, password)

        }
    }

    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}