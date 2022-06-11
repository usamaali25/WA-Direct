package com.example.wadirect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.cardview.widget.CardView


class SplashScreen : AppCompatActivity() {
    lateinit var cardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        cardView = findViewById(R.id.cardView)
        Handler().postDelayed({
            cardView.visibility = View.VISIBLE
        }, 2000)

        cardView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}