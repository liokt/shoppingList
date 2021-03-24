package com.example.lio.shoppinglist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lio.shoppinglist.R

class ShoppingFragment: Fragment(R.layout.fragment_shopping) {

    lateinit var viewmodel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
    }
}