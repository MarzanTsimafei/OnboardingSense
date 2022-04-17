package com.example.onboardingsense.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingsense.R

class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        supportActionBar?.hide()
    }
}