package com.example.lio.shoppinglist.repositories

import androidx.lifecycle.LiveData
import com.example.lio.shoppinglist.data.local.ShoppingDao
import com.example.lio.shoppinglist.data.local.ShoppingItem
import com.example.lio.shoppinglist.data.remote.responses.ImageResponse
import com.example.lio.shoppinglist.data.remote.responses.PixebayAPI
import com.example.lio.shoppinglist.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixebayAPI
): ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
       return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searcheForImage(imageQuery)
            if(response.isSuccessful){
                response.body()?.let{
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}