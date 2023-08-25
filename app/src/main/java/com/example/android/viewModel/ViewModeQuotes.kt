package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelQuotes: ViewModel() {

    var response = MutableLiveData<String>()
    var api = RetrofitHelper().getQuotesInstance().create(QuotesApi::class.java)
    init {
        getQuotesData()
    }
    fun getQuotesData() {

        try {
            viewModelScope.launch {
                var res = api.getQuotes()
                response.value = res.body()?.first()
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}