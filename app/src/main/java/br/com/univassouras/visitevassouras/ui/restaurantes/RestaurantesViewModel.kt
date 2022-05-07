package br.com.univassouras.visitevassouras.ui.restaurantes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is restaurant Fragment"
    }
    val text: LiveData<String> = _text
}