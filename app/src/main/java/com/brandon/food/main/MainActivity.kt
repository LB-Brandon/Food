package com.brandon.food.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brandon.food.mainRecipeDetail.MainRecipeDetailViewModel
import com.brandon.food.mainRecipeDetail.MainRecipeDetailViewModelFactory
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.inventory.InventoryActivity
import com.brandon.food.R
import com.brandon.food.databinding.ActivityMainBinding
import com.brandon.food.main.adapter.MainRecipeAdapter

object User {
    const val email: String = "michael@example.com"
}

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var backPressedTime: Long = 0
    private val backToast: Toast by lazy {
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT)
    }
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel()
                finishAffinity()
                return
            } else {
                backToast.show()
            }
            backPressedTime = System.currentTimeMillis()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callback)


        val viewModelFactory = MainActivityViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            MainActivityViewModel::
            class.java
        )

        val recyclerView: RecyclerView = binding.mainRecipesLl
        val adapter = MainRecipeAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.getItemListLiveData().observe(this) { itemList ->
            Log.d("MainActivity", "itemList: $itemList")
            adapter.setItems(itemList)
        }

        setClickListeners()

    }

    private fun setClickListeners() {
        binding.addIngredientsBtn.setOnClickListener {
            showRoundedBottomSheet()
        }
        binding.bottomAppBar.setNavigationOnClickListener {
            // 왼쪽 아이콘
        }
        binding.bottomAppBar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.calendar -> {
                    Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.inventory -> {
                    Toast.makeText(this, "Inventory", Toast.LENGTH_SHORT).show()
                    navigateToActivity(InventoryActivity::class.java)
                    true
                }
                else -> false
            }
        }

    }

    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    private fun showRoundedBottomSheet() {
        val bottomSheetFragment = MainRoundedBottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }


}

