package com.example.wellnessapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ ANIMATION GOES HERE
        val logo = findViewById<ImageView>(R.id.logo)
        val title = findViewById<TextView>(R.id.title)

        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        logo.startAnimation(anim)
        title.startAnimation(anim)

        // ⏳ SPLASH SCREEN TIMER (your code)
        Handler().postDelayed(
            {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish() // 🔥 important so user can't go back
            }, 5000
        )
    }
}