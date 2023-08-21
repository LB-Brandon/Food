package com.brandon.food.mainRecipeDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandon.food.databinding.ItemDetailIngredientBinding


class MainRecipeHaveAdapter(private val items: List<String>) :
    RecyclerView.Adapter<MainRecipeHaveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDetailIngredientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemDetailIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.ingredientNameTv.text = item
        }

    }

}
