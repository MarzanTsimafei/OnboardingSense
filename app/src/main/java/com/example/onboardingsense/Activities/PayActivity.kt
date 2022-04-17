package com.example.onboardingsense.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.ActivityMainBinding
import com.example.onboardingsense.databinding.ActivityPayBinding

class PayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.btnTry.setOnClickListener {
            val startmeditact =
                Intent(this, ShortMeditationActivity::class.java)
            startActivity(startmeditact)
        }
    }
}