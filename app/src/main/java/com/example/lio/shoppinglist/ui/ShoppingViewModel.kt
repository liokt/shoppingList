package com.example.lio.shoppinglist.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lio.shoppinglist.data.local.ShoppingItem
import com.example.lio.shoppinglist.data.remote.responses.ImageResponse
import com.example.lio.shoppinglist.other.Event
import com.example.lio.shoppinglist.other.Resource
import com.example.lio.shoppinglist.repositories.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository
): ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()
    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curlImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curlImageUrl

    private val _insertShoppingStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingStatus

    fun setCurrentImageUrl(url: String){
        _curlImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String){

    }

    fun searchForImage(imageQuery: String){

    }


}