package com.example.woof.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CardNumberTextField() {
    var cardNumber by remember { mutableStateOf("") }

    fun isValidCardNumber(value: String): Boolean {
        var checksum: Int = 0
        for (i in value.length - 1 downTo 0 step 2) {
            checksum += value[i] - '0'
        }
        for (i in value.length - 2 downTo 0 step 2) {
            val n: Int = (value[i] - '0') * 2
            checksum += if (n > 9) n - 9 else n
        }
        checksum % 10 == 0
        return checksum.toChar() == cardNumber.last()
    }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = {it -> cardNumber = it},
        //isError = isValidCardNumber(cardNumber),
        keyboardOptions = KeyboardOptions(keyboardType =
        KeyboardType.Number),
    )
}