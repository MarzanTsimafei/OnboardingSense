package com.example.onboardingsense.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.ActivityMainBinding
import com.example.onboardingsense.databinding.WelcomeActivityBinding


class WelcomeActivity : AppCompatActivity() {


    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

       val animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.welcome_anim);

        binding.qqq.startAnimation(animFadeIn)

        Handler().postDelayed(Runnable {
            val startLandingPageActivity =
                Intent(this, MainActivity::class.java)
            startActivity(startLandingPageActivity)

        }, 5000)

    }
}