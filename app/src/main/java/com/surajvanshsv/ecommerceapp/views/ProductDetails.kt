package com.surajvanshsv.ecommerceapp.views

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.surajvanshsv.ecommerceapp.R
import com.surajvanshsv.ecommerceapp.databinding.ActivityProductDetailsBinding
import com.surajvanshsv.ecommerceapp.model.Product
import com.surajvanshsv.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetails : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val viewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the product details from the intent
        val productTitle = intent.getStringExtra("productTitle") ?: ""
        val productPrice = intent.getDoubleExtra("productPrice", 0.0)
        val productImageUrl = intent.getStringExtra("productImageUrl") ?: ""
        val productID = intent.getStringExtra("productID") ?: "0"


        // display the product details
        binding.productTitleDetail.text = productTitle
        binding.productPriceDetail.text = "$ $productPrice"


        // load the product image
        Glide.with(this)
            .load(productImageUrl)
            .into(binding.productImageDetail)

        // handle click event in card button
        binding.addToCartButton.setOnClickListener {
            addToCart(Product(productID, productTitle, productPrice, productImageUrl))
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun addToCart(product: Product){
        // insert item into room database
        viewModel.addToCart(product)
        Toast.makeText(this,"Added to Cart",Toast.LENGTH_SHORT).show()

    }

}