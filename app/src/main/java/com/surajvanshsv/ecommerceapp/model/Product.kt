package com.surajvanshsv.ecommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart_items")
data class Product(

    @PrimaryKey
    val id: String = "",
    val title: String ="",
    val price: Double = 0.0,
    val imageUrl : String = ""

)
