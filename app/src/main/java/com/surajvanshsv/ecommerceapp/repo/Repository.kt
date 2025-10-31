package com.surajvanshsv.ecommerceapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.surajvanshsv.ecommerceapp.R
import com.surajvanshsv.ecommerceapp.model.Category
import com.surajvanshsv.ecommerceapp.model.Product
import com.surajvanshsv.ecommerceapp.room.CartDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


// centralised data opertions (Firebase and Room)
class Repository @Inject constructor(val firestore : FirebaseFirestore ,
                                     val cartDao: CartDao){


    // fetch categories from firestore
    fun fetchCategories() : MutableLiveData<List<Category>>{
        var categoriesList = MutableLiveData<List<Category>>()


        // when fetching categories from firestore , app will check if category name exist in catImages this map , and then assign the correct image to it .
        val catImages = mapOf(
            "Electronics" to R.drawable.electronics,
            "Jewelry" to R.drawable.jewelery,
            "Men" to R.drawable.mensclothing,
            "Women" to R.drawable.womenclothing,
            "Cosmetics" to R.drawable.cosmetics,
            "Shoes" to R.drawable.runningshoes,
            "Toys" to R.drawable.toys,
            "Tools" to R.drawable.tools,
            "Home" to R.drawable.sofa,
            "Automotive" to R.drawable.brake
        )

        //fetching data from firestore
        firestore.collection("categories")
            .get() // retrives data asynchonously
            .addOnSuccessListener {documents ->
                var category = documents.map { document ->

                    // get the image resources from the map using document.id as the key
                    val imageRes = catImages[document.id] ?: R.drawable.ic_launcher_background

                    Category(document.id,imageRes)
                }

                // updating live data and logging
                // postValue: Updates the value asynchronously
                // which will notify any observers (UI)
                categoriesList.postValue(category)
                Log.v("TAGY","category: $categoriesList")
                }
                return categoriesList
    }


    // Firebase : fetch products from firestore
    fun fetchProducts(categoryName: String) : MutableLiveData<List<Product>>{
        val productsList = MutableLiveData<List<Product>>()

        firestore.collection("categories")
            .document(categoryName)
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val products = documents.map { document ->
                          Product(
                              id =document.id,
                              title = document.getString("title") ?: "",
                              price = document.getDouble("price") ?: 0.0,
                              imageUrl = document.getString("imageUrl") ?: ""

                          )
                }
                // updating the live data and logging , postValue: Updates the value asynchronously
                // which will notify any observers (UI)
                // jo bhi product server se aaya usko upper waale variable me daalo fir ussko return karo
                productsList.postValue(products)
            }
        return productsList
    }

    // Room : fetch cart items from room database
    suspend fun getCartItems() : List<Product>{
        return cartDao.getCardItems()
    }

   // add product to cart
    suspend fun addToCart(product: Product){
        cartDao.addToCart(product)
    }

    // remove product from cart
    suspend fun removeFromCart(productId: String){
        cartDao.removeFromCart(productId)
    }

    // clear cart
    suspend fun clearCart(){
        cartDao.clearCart()
    }


    // upload products to firestore
    fun savePurchasesInFirestore(product: Product){

        firestore.collection("purchases")
            .add(product)
             .addOnSuccessListener {
                 //clear the cart if we want to add all products to purchase list
                 CoroutineScope(Dispatchers.IO).launch { clearCart() }

             }
    }

}
