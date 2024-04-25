package com.example.lotusaddictionapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Payment2Activity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment2)

        //CONTINUE BUTTON
        val continueBtn = findViewById<Button>(R.id.continueButton)
        continueBtn.setOnClickListener {
            println("Continue clicked in Payment2Activity")

            //this is where we would put the logic for handling payment, but we arent going to do anything for that here at this time

            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent) //start up the payment activity
        }
    }
}