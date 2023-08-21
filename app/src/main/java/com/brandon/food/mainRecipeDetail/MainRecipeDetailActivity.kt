package com.brandon.food.mainRecipeDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brandon.food.databinding.ActivityMainRecipeDetailBinding
import com.brandon.food.main.User
import com.brandon.food.main.model.MainRecipe
import com.brandon.food.mainRecipeDetail.adapter.MainRecipeHaveAdapter
import com.brandon.food.mainRecipeDetail.adapter.MainRecipeMissingAdapter
import com.bumptech.glide.Glide
import java.net.URLEncoder

class MainRecipeDetailActivity : AppCompatActivity() {
    private val TAG = "MainRecipeDetailActivity"
    private lateinit var binding: ActivityMainRecipeDetailBinding
    private lateinit var missingIngredientRecyclerView: RecyclerView
    private lateinit var haveIngredientRecyclerView: RecyclerView
    private lateinit var recipe: MainRecipe
    private lateinit var viewModel: MainRecipeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()

        val viewModelFactory = MainRecipeDetailViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            MainRecipeDetailViewModel::
            class.java
        )

        recipe = intent.getSerializableExtra("recipe") as MainRecipe
        recipe?.let {
            viewModel.updateMainRecipe(it)
            Log.i(TAG, "onCreate: $it")
            val queryParam = URLEncoder.encode(it.name, "utf-8")
            viewModel.fetchIngredientDetail(User.email, queryParam){ success->
                if(success){
                    Log.i(TAG, "onCreate: fetchIngredientDetail success")
                    updateUI(it)
                }else{
                    Log.i(TAG, "onCreate: fetchIngredientDetail failed")
                }
            }
        }
    }


    private fun updateUI(item: MainRecipe?) {
        Log.i(TAG, "updateUI: $item")
        binding.recipeDetailNameTv.text = item?.name
        binding.recipeDetailTimeTv.text = item?.time.toString() + "min"
        Glide.with(this)
            .load(item?.imageUrl)
            .into(binding.recipeDetailImage)

        setupMissingIngredientRecyclerView()
        setupHaveIngredientRecyclerView()
    }

    private fun setupMissingIngredientRecyclerView() {
        val missingIngredients: List<String>? = viewModel.getMissingIngredients()
        val adapter = missingIngredients?.let { MainRecipeMissingAdapter(it) }

        missingIngredientRecyclerView = binding.missingIngredientsRv
        missingIngredientRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        missingIngredientRecyclerView.adapter = adapter
    }

    private fun setupHaveIngredientRecyclerView() {
        val haveIngredients: List<String>? = viewModel.getHaveIngredients()
        val adapter = haveIngredients?.let { MainRecipeHaveAdapter(it) }

        haveIngredientRecyclerView = binding.haveIngredientsRv
        haveIngredientRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        haveIngredientRecyclerView.adapter = adapter
    }

    private fun setClickListeners() {
        binding.ingredientsCv.setOnClickListener {
            val hiddenView = binding.hiddenViewIngredientsLl

            var status =
                if (hiddenView.visibility == View.GONE) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(
                hiddenView.parent as ViewGroup,
                AutoTransition()
            )
            hiddenView.visibility = status
        }
    }


}