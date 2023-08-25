package com.example.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotFragment = QuoteFragment.newInstance("","")
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container1, quotFragment, "quoteFragment").commit()
    }
}