package com.surajvanshsv.ecommerceapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.surajvanshsv.ecommerceapp.model.Product


@Dao
interface CartDao {

    @Insert
    suspend fun addToCart(cartItem : Product)

    @Query("SELECT * FROM cart_items")
    suspend fun getCardItems() : List<Product>

    @Query("DELETE FROM cart_items WHERE id = :productId")
    suspend fun removeFromCart(productId : String)


    @Query("DELETE FROM cart_items")
    suspend fun clearCart()


}