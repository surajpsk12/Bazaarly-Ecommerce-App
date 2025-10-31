package com.surajvanshsv.ecommerceapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajvanshsv.ecommerceapp.R
import com.surajvanshsv.ecommerceapp.databinding.ActivityCategoryItemsBinding
import com.surajvanshsv.ecommerceapp.databinding.ActivityMainBinding
import com.surajvanshsv.ecommerceapp.model.Product
import com.surajvanshsv.ecommerceapp.util.CategoryAdapter
import com.surajvanshsv.ecommerceapp.util.ProductAdapter
import com.surajvanshsv.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue


//responsile for displaying products of a category
@AndroidEntryPoint
class CategoryItems : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryItemsBinding
    private lateinit var productAdapter: ProductAdapter
    private val viewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryItemsBinding
            .inflate(layoutInflater)
        setContentView(binding.root)

        // set up recycler view for products
        productAdapter = ProductAdapter { selectedProduct ->
            // handle product click
            onProductClick(selectedProduct)
        }
        binding.recyclerViewCategory.adapter = productAdapter
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(this)

        // get category name from the intent
        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: ""


        // fetch products
        val result = viewModel.fetchProducts(categoryName)

        result.observe(this) { newProductList ->
            if (newProductList.isNotEmpty()) {
                productAdapter.submitList(newProductList)
            } else {
                // handle empty product list
                Log.v("TAGY", "No products found for category: $categoryName")
            }
        }






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun onProductClick(selectedProduct: Product){
        // handle product click
        // create an intent to share the product details activity
        val intent = Intent(this, ProductDetails::class.java)
        intent.putExtra("productTitle", selectedProduct.title)
        intent.putExtra("productPrice", selectedProduct.price)
        intent.putExtra("productImageUrl", selectedProduct.imageUrl)
        intent.putExtra("productID", selectedProduct.id)
        startActivity(intent)
        Log.v("TAGY", "Product")
    }
}