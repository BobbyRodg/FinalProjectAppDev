package com.example.woof.ui.pages

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.woof.R
import com.example.woof.RetroViewModel
import com.example.woof.ui.components.RetroButton

@Composable
fun LoginPage(whichScreen: Int, onChangeScreen: (Int) -> Unit, viewModel: RetroViewModel) {
    var user by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var thisVisible by remember { mutableStateOf(true) }
    var signUpVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current


    AnimatedVisibility(
        visible = thisVisible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { onChangeScreen(1) }, // Navigate back to the previous screen
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "Back button"
                    )
                }
            }
            Text(text = "Login to your account")
            OutlinedTextField(
                user,
                label = { Text(text = "Username", color = Color.Gray) },
                onValueChange = { newText ->
                    user = newText
                },
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary)
            )

            OutlinedTextField(
                pass,
                label = { Text(text = "Password", color = Color.Gray) },
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
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RetroButton(onClick = {

                    val isValid = viewModel.validateUser(user.text, pass.text)
                    if (isValid) {
                        // Login successful (navigate to home screen or similar)
                        Toast.makeText(context, "Hello ${user.text}", Toast.LENGTH_SHORT).show()
                        onChangeScreen(1)
                    } else {
                        // Login failed (show error message)
                        Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, modifier = Modifier.padding(30.dp)) {
                    Text(text = "Log in")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RetroButton(onClick = {
                    signUpVisible = true
                    thisVisible = false
                }, modifier = Modifier.padding(30.dp)) {
                    Text(text = "Sign Up")
                }
            }
        }
    }
    fun onReturn() {
        signUpVisible = false
        thisVisible = true
    }
    AnimatedVisibility(
        visible = signUpVisible,
        enter = slideInVertically(),
        exit = slideOutVertically() + fadeOut(),
    ) {
        SignUpPage(
            whichScreen = whichScreen,
            onChangeScreen = onChangeScreen,
            viewModel = viewModel,
            onReturn = { onReturn() })
    }
}

