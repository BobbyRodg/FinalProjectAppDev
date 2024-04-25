package com.example.lotusaddictionapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lotusaddictionapp.R.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.register)

        val viewModel: LotusViewModel by viewModels()

        val continueBtn = findViewById<Button>(id.buttonContinue) //not to be confused with continueButton from the payment activity



        continueBtn.setOnClickListener{
            println("Continue clicked in RegisterActivity")
            val firstName = ( findViewById<EditText>(id.editTextFirstName) ).getText().toString()
            val lastName = ( findViewById<EditText>(id.editTextLastName) ).getText().toString()
            val email = ( findViewById<EditText>(id.editTextEmailAddress2) ).getText().toString()
            val pass = ( findViewById<EditText>(id.editTextPassword3) ).getText().toString()

            if(isValidInput(firstName,lastName,email,pass)){
                viewModel.addUser(
                    first = firstName,
                    last = lastName,
                    email = email,
                    pass = pass
                )
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent) //start up the home activity
            }


            /*
            REGISTER LOGIC STILL NEEDED
            What we need here is the things to handle the register logic, could maybe use a json file for this.
            Or a database honestly, whatever we are more comfortable using probably
            For now I'm just going to have it redirect to the welcome page probably
            */
        }

    }

}

fun isValidInput(firstName: String, lastName: String, user: String, pass: String) : Boolean{
    return (firstName.trim().isNotEmpty() &&
            lastName.trim().isNotEmpty() &&
            user.trim().isNotEmpty() &&
            pass.trim().isNotEmpty()
            )
}