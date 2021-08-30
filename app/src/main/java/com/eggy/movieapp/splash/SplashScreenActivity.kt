package com.eggy.movieapp.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.eggy.movieapp.MainActivity
import com.eggy.movieapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
    }
}