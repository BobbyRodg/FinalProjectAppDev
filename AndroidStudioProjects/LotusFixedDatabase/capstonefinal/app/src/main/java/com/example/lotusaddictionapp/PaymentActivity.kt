package com.example.lotusaddictionapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        //CONTINUE BUTTON
        val completeBtn = findViewById<Button>(R.id.completeButton) //not to be confused with buttonContinue from the register activity
        completeBtn.setOnClickListener{
            println("Complete clicked in PaymentActivity")

            //this is where we would put the logic for handling payment, but we arent going to do anything for that here at this time

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent) //start up the home activity
        }
    }
}