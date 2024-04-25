package com.example.lotusaddictionapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        val startBtn = findViewById<Button>(R.id.startButton)
        startBtn.setOnClickListener{
            println("Get Started button clicked in WelcomeActivity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) //start up the registration activity
        }
    }
}