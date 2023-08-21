package com.brandon.food.inventory

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandon.food.R
import com.brandon.food.databinding.ItemInventoryIngredientBinding
import com.brandon.food.inventory.model.InventoryIngredient

class InventoryAdapter(private val viewModel: InventoryViewModel) :
    RecyclerView.Adapter<InventoryViewHolder>() {
    private var items: List<InventoryIngredient> = emptyList()

    fun setItems(items: List<InventoryIngredient>) {
        this.items = items
        Log.i("InventoryAdapter", "items: ${this.items}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory_ingredient, parent, false)
        return InventoryViewHolder(ItemInventoryIngredientBinding.bind(view), viewModel)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class InventoryViewHolder(
    val binding: ItemInventoryIngredientBinding,
    private val viewModel: InventoryViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(inventoryIngredient: InventoryIngredient) {
        Log.i("InventoryViewModelHolder", "inventoryIngredient: $inventoryIngredient")
        binding.ingredientNameTv.text = inventoryIngredient.name
        binding.ingredientDeleteBtn.setOnClickListener {
            viewModel.deleteInventoryIngredient(inventoryIngredient)
        }
    }
}