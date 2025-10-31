package com.surajvanshsv.ecommerceapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajvanshsv.ecommerceapp.model.Category
import com.surajvanshsv.ecommerceapp.model.Product
import com.surajvanshsv.ecommerceapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    // fetch categories from repository
    fun fetchCategories() : MutableLiveData<List<Category>>{
        return repository.fetchCategories()
    }


    // fetch products
    fun fetchProducts(categoryName: String) : MutableLiveData<List<Product>>{
        return repository.fetchProducts(categoryName)

    }

    // add to cart
    fun addToCart(cartItem: Product){
        viewModelScope.launch {
            repository.addToCart(cartItem)
        }
    }


    // remove from cart
    fun removeFromCart(productId: String) {
        viewModelScope.launch {
            repository.removeFromCart(productId)
        }
    }

    // clear cart
    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

   // get cart items
   fun getCartItems() : MutableLiveData<List<Product>>{
       val cartItems = MutableLiveData<List<Product>>()
       viewModelScope.launch {
           val items = repository.getCartItems()
           cartItems.postValue(items)
       }
       return cartItems

   }


    fun savePurchaseInFirestore(product: Product){
        repository.savePurchasesInFirestore(product)
    }

}


