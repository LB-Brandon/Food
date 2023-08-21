package com.brandon.food.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.brandon.food.apiservice.FoodApi
import com.brandon.food.databinding.ActivityInventoryBinding
import com.brandon.food.main.User

class InventoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInventoryBinding
    private lateinit var viewModel: InventoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = InventoryViewModelFactory(FoodApi.service)
        viewModel = ViewModelProvider(this, viewModelFactory).get(InventoryViewModel::class.java)
        val recyclerView = binding.userInventoryRv
        val adapter = InventoryAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // add Live data
        viewModel.getItemListLiveData().observe(this) {itemList ->
            Log.i("InventoryActivity", "itemList: $itemList")
            adapter.setItems(itemList)
        }
        // fetch data
        viewModel.fetchInventoryIngredients(User.email)
        // add click listener
        setClickListeners()

    }

    private fun setClickListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}