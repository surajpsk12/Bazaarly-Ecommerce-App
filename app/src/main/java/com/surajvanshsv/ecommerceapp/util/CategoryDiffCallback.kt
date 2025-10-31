package com.surajvanshsv.ecommerceapp.util

import androidx.recyclerview.widget.DiffUtil
import com.surajvanshsv.ecommerceapp.model.Category

// used to efficiently update the recycler view when the data changes ,
// this is the part of DiffUtil , which helps in optimised list updates instead of reloading everything ,
// diff util helps calculate the differences btwn old and new list and only update the changed items.
class CategoryDiffCallback : DiffUtil.ItemCallback<Category>()  {
    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem == newItem
    }

}
