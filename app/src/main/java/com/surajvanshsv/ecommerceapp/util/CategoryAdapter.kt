package com.surajvanshsv.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surajvanshsv.ecommerceapp.databinding.ItemCategoryBinding
import com.surajvanshsv.ecommerceapp.model.Category

class CategoryAdapter (
    private val onCategoryClick: (String) -> Unit
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {

        // getting the name of the category
        val category = getItem(position)

        // displaying the name here
        holder.binding.categoryName.text = category.name

        // displaying the image here
        Glide.with(holder.itemView.context)
            .load(category.catImg)
            .into(holder.categoryImage)

        // handling the click event
        holder.itemView.setOnClickListener {
            onCategoryClick(category.name)
        }
    }



    // create view holder
    class CategoryViewHolder(val binding: ItemCategoryBinding)
        : RecyclerView.ViewHolder(binding.root){
            // bind data to view holder
            val categoryImage = binding.imageViewCategory
        }



}