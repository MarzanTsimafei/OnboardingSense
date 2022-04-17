package com.example.onboardingsense.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingsense.R

class ShortMeditationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_short_meditation)
        supportActionBar?.hide()

    }
}