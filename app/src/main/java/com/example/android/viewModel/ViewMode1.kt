package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel1: ViewModel() {

    var response = MutableLiveData<List<String>>()
    var api = RetrofitHelper().getInstance().create(QuotesApi::class.java)

    fun getData() {
        try {
            viewModelScope.launch {
                var res = api.getQuotes()
                response.value = res.body()
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}