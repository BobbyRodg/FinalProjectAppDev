package com.example.woof.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.woof.R
import com.example.woof.RetroViewModel
import com.example.woof.data.User
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SignUpPage(whichScreen: Int, onChangeScreen: (Int) -> Unit, viewModel: RetroViewModel, onReturn: () -> Unit) {
    var user by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
        //modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(
                onClick = { onReturn() }, // Navigate back to the previous screen
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back button"
                )
            }
        }
        Text(text="Enter your details to sign up")
        OutlinedTextField( firstName,
            label ={Text(text = "First Name", color= Color.Gray)},
            onValueChange = { newText ->
                firstName = newText
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary))

        OutlinedTextField( lastName,
            label ={Text(text = "Last Name", color= Color.Gray)},
            onValueChange = { newText ->
                lastName = newText
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary))

        OutlinedTextField( user,
            label ={Text(text = "Username:", color= Color.Gray)},
            onValueChange = { newText ->
                user = newText
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary))

        OutlinedTextField( pass,
            label ={Text(text = "Password:", color= Color.Gray)},
            onValueChange = { newText ->
                pass = newText
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = {
                if(isValidInput(firstName.text, lastName.text, user.text, pass.text)) {
                    viewModel.addUser(
                        first = firstName.text,
                        last = lastName.text,
                        username = user.text,
                        pass = pass.text
                    )
                    onReturn()
                }
                }, modifier = Modifier.padding(30.dp)) {

                Text(text="Sign up")
            }
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
