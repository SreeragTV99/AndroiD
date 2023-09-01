package com.example.android.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.android.R
import com.example.android.model.LoginRes
import com.example.android.viewModel.ViewModelUsers
import com.google.gson.Gson
import android.widget.Button as Button

lateinit var loginViewmodel: ViewModelUsers
class LoginFragment : Fragment() {

    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginBtn: Button
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_login, container, false)
        with(inflatedView) {
            usernameEditText = findViewById(R.id.usernameInput)
            passwordEditText = findViewById(R.id.passwordInput)
            loginBtn = findViewById(R.id.loginButton)
        }
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        loginViewmodel = ViewModelProvider(this).get(ViewModelUsers::class.java)
        loginBtn.setOnClickListener {
            loginViewmodel.apply {
                val isValid = validateInput(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
                if (!isValid) {
                    Toast.makeText(requireContext(), "Please Enter credentials!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    loginUser(
                        usernameEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
                }
            }
        }

        loginViewmodel.liveData.observe(viewLifecycleOwner) { result ->
            if (result) {
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                var intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }
        }
        loginViewmodel.apiResponse.observe(viewLifecycleOwner) { res ->
            run{
                sharedPreferencesData(res)
        }
        }
    }
    fun sharedPreferencesData(data: LoginRes){
        sharedPreferences=requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val finaluserData= Gson().toJson(data)
        editor.putString("shared_data", finaluserData)
        editor.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}