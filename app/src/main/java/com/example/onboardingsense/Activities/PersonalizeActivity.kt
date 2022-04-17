package com.example.onboardingsense.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
        binding.indicatorPerson.show()

        Handler().postDelayed(Runnable {
            val startLandingPageActivity =
                Intent(this, PayActivity::class.java)
            startActivity(startLandingPageActivity)

        }, 5000)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.indicatorPerson.hide()
    }
}