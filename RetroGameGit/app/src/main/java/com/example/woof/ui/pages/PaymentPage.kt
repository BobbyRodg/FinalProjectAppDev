package com.example.woof.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.woof.R
import com.example.woof.ui.components.CardNumberTextField

@Composable
fun PaymentPage(whichScreen: Int, onChangeScreen: (Int) -> Unit) {
    var first by remember { mutableStateOf(TextFieldValue("")) }
    var last by remember { mutableStateOf(TextFieldValue("")) }
    var card by remember { mutableStateOf(TextFieldValue("")) }
    var expiry by remember { mutableStateOf(TextFieldValue("MM/YY")) }
    var country by remember { mutableStateOf(TextFieldValue("Canada")) }
    var postalCode by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { onChangeScreen(1) }, // Navigate back to RetroGameApp page
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back button"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Complete Payment",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(text = "First Name", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField( first,
                onValueChange = { newText ->
                                first = newText
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Last Name", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = last,
                onValueChange = { newText ->
                    last = newText
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Card Number", style = MaterialTheme.typography.bodyMedium)
                Text(text = "MM/YY", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardNumberTextField()
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = expiry, // Replace with actual value
                    onValueChange = { newText ->
                        expiry = newText
                    },
                    modifier = Modifier.weight(0.3f)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Country", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Postal Code", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = country, // Replace with actual value
                    onValueChange = { newText ->
                        first = newText
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = postalCode, // Replace with actual value
                    onValueChange = { newText ->
                        postalCode = newText
                    },
                    modifier = Modifier.weight(0.3f)
                )
            }
        }
        Button(
            onClick = { onChangeScreen(1) }, // Navigate back to RetroGameApp page
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(text = "Complete")
        }
    }
}