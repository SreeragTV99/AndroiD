package com.example.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.beginTransaction().replace(R.id.containerLogin,LoginFragment()).commit()
    }
}