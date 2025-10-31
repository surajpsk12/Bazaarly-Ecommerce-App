package com.surajvanshsv.ecommerceapp.di

import android.content.Context
import com.surajvanshsv.ecommerceapp.room.CartDao
import com.surajvanshsv.ecommerceapp.room.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context): CartDatabase {
        return CartDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDAO(database: CartDatabase): CartDao {
        return database.cartDao()
    }



}