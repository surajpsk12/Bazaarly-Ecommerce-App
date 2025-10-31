package com.surajvanshsv.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajvanshsv.ecommerceapp.databinding.ActivityMainBinding
import com.surajvanshsv.ecommerceapp.util.CategoryAdapter
import com.surajvanshsv.ecommerceapp.viewmodel.MyViewModel
import com.surajvanshsv.ecommerceapp.views.CartActivity
import com.surajvanshsv.ecommerceapp.views.CategoryItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // cart button
        binding.viewCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        // setup recycler view
        categoryAdapter = CategoryAdapter{categoryName ->
            // handle category click
            // navigate to category products
            onCategoryClick(categoryName)
        }
        binding.recyclerView.adapter = categoryAdapter

        binding.recyclerView.layoutManager = GridLayoutManager(this,2)

        val result = viewModel.fetchCategories()
        result.observe(this) { newCategoriesList ->
            if(newCategoriesList.isNotEmpty()){
            categoryAdapter.submitList(newCategoriesList)
        }else {
            Toast.makeText(this, "No categories found", Toast.LENGTH_SHORT).show()
        }
            }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onCategoryClick(categoryName: String) {
        // handle category click
        val intent = Intent(this, CategoryItems::class.java)
        intent.putExtra("CATEGORY_NAME", categoryName)
        startActivity(intent)
        Toast.makeText(this, "Category clicked: $categoryName", Toast.LENGTH_SHORT).show()
    }

}

