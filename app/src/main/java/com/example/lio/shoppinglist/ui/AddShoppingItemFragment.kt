package com.example.lio.shoppinglist.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lio.shoppinglist.R
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*

class AddShoppingItemFragment: Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewmodel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

        ivShoppingImage.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewmodel.setCurrentImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}
