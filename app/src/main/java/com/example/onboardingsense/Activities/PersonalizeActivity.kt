package com.example.onboardingsense.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingsense.databinding.ActivityPersonalizeBinding
import com.example.onboardingsense.databinding.WelcomeActivityBinding

class PersonalizeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()


    }
}