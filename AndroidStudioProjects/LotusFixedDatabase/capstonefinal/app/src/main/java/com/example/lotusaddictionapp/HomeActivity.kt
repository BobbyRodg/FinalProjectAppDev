package com.example.lotusaddictionapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        //VIDEOS BUTTON
        val videosBtn = findViewById<Button>(R.id.videoButton)
        videosBtn.setOnClickListener{
            println("Videos clicked in HomeActivity")
            val intent = Intent(this, VideosActivity::class.java)
            startActivity(intent) //start up the videos activity
        }

        //PLANS BUTTON
        val plansBtn = findViewById<Button>(R.id.plansButton)
        plansBtn.setOnClickListener{
            println("Plans clicked in HomeActivity")
            val intent = Intent(this, Payment2Activity::class.java)
            startActivity(intent) //start up the payment2 activity
        }

        //CALENDAR BUTTON
        val calendarBtn = findViewById<Button>(R.id.calendarButton)
        calendarBtn.setOnClickListener{
            println("Calendar clicked in HomeActivity")
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent) //start up the calendar activity
        }

    }
}