package com.example.lotusaddictionapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val viewModel: LotusViewModel by viewModels()

        // Create default user for testing purposes
        viewModel.createDefaultUser()

        //REGISTER BUTTON
        val registerBtn = findViewById<Button>(R.id.registerButton)
        registerBtn.setOnClickListener{
            println("Register clicked in LoginActivity")
            val intent = Intent(this, RegisterActivity::class.java)//I've never used kotlin much, but can I still just use class.java here without any problems?
            startActivity(intent) //start up the registration activity
        }

        //LOGIN BUTTON
        val loginBtn = findViewById<Button>(R.id.loginButton)
        loginBtn.setOnClickListener{
            val email = ( findViewById<EditText>(R.id.editTextEmailAddress) ).getText().toString()
            val pass = ( findViewById<EditText>(R.id.editTextPassword) ).getText().toString()
            println("Login clicked in LoginActivity")

            val isValid = viewModel.validateUser(email, pass)
            if (isValid.first) {
                val authenticatedUserFirstName = viewModel.getUserByEmail(email).firstName
                // Login successful (navigate to home screen or similar)
                Toast.makeText(this, "Hello, ${authenticatedUserFirstName}!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent) //start up the home activity
            } else {
                // Login failed (show error message)
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }


}
