package br.com.univassouras.visitevassouras.ui.eventos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventosViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is event Fragment"
    }
    val text: LiveData<String> = _text
}