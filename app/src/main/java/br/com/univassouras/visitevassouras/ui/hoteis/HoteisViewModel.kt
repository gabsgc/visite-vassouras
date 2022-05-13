package br.com.univassouras.visitevassouras.ui.hoteis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HoteisViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is hotel Fragment"
    }
    val text: LiveData<String> = _text
}