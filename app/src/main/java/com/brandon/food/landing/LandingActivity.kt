package com.brandon.food.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brandon.food.databinding.ActivityLandingBinding
import com.brandon.food.main.MainActivity
import com.brandon.food.signin.SignInActivity
import com.brandon.food.singup.SignUpActivity

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.registerBtnCl.setOnClickListener {
            navigateToActivity(SignUpActivity::class.java)
        }

        binding.loginBtnCl.setOnClickListener {
            navigateToActivity(SignInActivity::class.java)
        }

        binding.mainBgIv.setOnClickListener {
            navigateToActivity(MainActivity::class.java)
        }
    }

    private fun navigateToActivity(activityClass : Class<*>){
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

}