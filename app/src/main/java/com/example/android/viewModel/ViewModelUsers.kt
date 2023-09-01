package com.example.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.model.LoginRequest
import com.example.android.model.LoginRes
import kotlinx.coroutines.launch

class ViewModelUsers: ViewModel(){

    var liveData = MutableLiveData<Boolean>()
    var apiResponse = MutableLiveData<LoginRes>()
    val loginApi = RetrofitHelper().getUsersInstance().create(UsersApi::class.java)
    fun validateInput(username: String, password: String): Boolean {
        return !(username.isEmpty() || password.isEmpty())
    }

    fun loginUser(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
        viewModelScope.launch {
            try {
                var res = loginApi.getUsers(loginRequest)
                liveData.value = res.isSuccessful
                apiResponse.value=res.body()
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
