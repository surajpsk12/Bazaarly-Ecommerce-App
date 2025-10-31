package com.surajvanshsv.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surajvanshsv.ecommerceapp.databinding.ItemCartBinding
import com.surajvanshsv.ecommerceapp.model.Product


//start from cart activity
class CartAdapter(
    private val onRemoveFromCartClick: (Product) -> Unit // callback for item removal
) : ListAdapter<Product, CartAdapter.CartViewHolder>(ProductDiffCallback()) {


    class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

            val cartItemImage = binding.cartItemImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.cartItemTitle.text = product.title
        holder.binding.cartItemPrice.text = product.price.toString()

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.cartItemImage)

        holder.binding.removeCartItemButton.setOnClickListener {
            onRemoveFromCartClick(product)
        }
    }
}





