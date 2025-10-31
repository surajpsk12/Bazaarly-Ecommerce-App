package com.surajvanshsv.ecommerceapp.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajvanshsv.ecommerceapp.databinding.ActivityCartBinding
import com.surajvanshsv.ecommerceapp.model.Product
import com.surajvanshsv.ecommerceapp.util.CartAdapter
import com.surajvanshsv.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel: MyViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initializing the adapter
        cartAdapter = CartAdapter() {
            // Pass the remove function to the adapter
            cartItem -> removeCartItem(cartItem)
        }


        binding.recyclerViewCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(this@CartActivity)
        }

        binding.clearCartButton.setOnClickListener {
            viewModel.clearCart()

            // Clear the adapter's data
            cartAdapter.submitList(emptyList())
        }
        binding.checkOutButton.setOnClickListener {
            // Handle checkout logic
            checkOutCart()
        }

    //    update gradle plugin
        // Fetch cart items
        viewModel.getCartItems().observe(this) { cartItems ->
            cartAdapter.submitList(cartItems)
        }




    }

    // Removing the cart item
    private fun removeCartItem(cartItem: Product) {

        viewModel.removeFromCart(cartItem.id)

        val updatedCartItems = viewModel.getCartItems()
            .value?.toMutableList()

        cartAdapter.submitList(updatedCartItems)


    }

    // Upload purchased items to Firestore
    private fun checkOutCart(){
        // Get the list of purchased items
        viewModel.getCartItems().observe(this){
            purchasedItems ->

            for (item in purchasedItems){
                viewModel.savePurchaseInFirestore(item)
            }
        }




    }





}