package com.brandon.food.main.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandon.food.mainRecipeDetail.MainRecipeDetailActivity
import com.brandon.food.mainRecipeDetail.MainRecipeDetailViewModel
import com.brandon.food.R
import com.brandon.food.databinding.ItemMainRecipeBinding
import com.brandon.food.main.MainActivityViewModel
import com.brandon.food.main.model.MainRecipe
import com.bumptech.glide.Glide

class MainRecipeAdapter(private val viewModel: MainActivityViewModel) : RecyclerView.Adapter<MainRecipeAdapter.MainRecipeViewHolder>() {
    private var items: List<MainRecipe> = emptyList()

    fun setItems(items: List<MainRecipe>) {
        this.items = items
        Log.d("MainRecipeAdapter", "items: ${this.items}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_recipe, parent, false)
        return MainRecipeViewHolder(ItemMainRecipeBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MainRecipeViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MainRecipeViewHolder(val binding: ItemMainRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // add click listener to each item
        init {
            binding.mainRecipeCard.setOnClickListener(View.OnClickListener {
                val recipe = viewModel.getRecipeByPosition(bindingAdapterPosition)
                val intent = Intent(binding.root.context, MainRecipeDetailActivity::class.java)
                Log.i("MainRecipeViewHolder", "recipe: $recipe")
                intent.putExtra("recipe", recipe as java.io.Serializable)
                binding.root.context.startActivity(intent)
            })

        }

        fun bind(mainRecipe: MainRecipe) {
            Log.d("MainRecipeViewHolder", "mainRecipe: $mainRecipe")
            binding.mainRecipeTitleTv.text = mainRecipe.name
            binding.mainRecipeTimeTv.text = "${mainRecipe.time} Min"
            binding.mainRecipeServingsTv.text = "${mainRecipe.serving} Serve"
            val imageUrl = mainRecipe.imageUrl
            Log.d("MainRecipeViewHolder", "imageUrl: $imageUrl")

            Glide.with(binding.mainRecipeImg)
                .load(imageUrl)
                .into(binding.mainRecipeImg)

        }

    }
}



