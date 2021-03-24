package com.example.lio.shoppinglist.repositories

import androidx.lifecycle.LiveData
import com.example.lio.shoppinglist.data.local.ShoppingItem
import com.example.lio.shoppinglist.data.remote.responses.ImageResponse
import com.example.lio.shoppinglist.di.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}