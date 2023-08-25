package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelQuotes: ViewModel() {

    val response = MutableLiveData<String>()
    val api = RetrofitHelper().getQuotesInstance().create(QuotesApi::class.java)

    fun getQuotesData() {
        viewModelScope.launch {
            val res = api.getQuotes()
            response.value = res.body()?.first()
        }
    }
}