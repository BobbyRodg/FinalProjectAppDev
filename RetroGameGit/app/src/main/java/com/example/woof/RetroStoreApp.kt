package com.example.woof

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.woof.ui.pages.BuyPage
import com.example.woof.ui.pages.FilterPage
import com.example.woof.ui.pages.LoginPage
import com.example.woof.ui.pages.PaymentPage
import com.example.woof.ui.pages.SignUpPage

@Composable
fun RetroStoreApp() {
    var whichScreen by remember { mutableStateOf(1) }
    var consoleFilter by remember { mutableStateOf("all") }
    var conditionFilter by remember { mutableStateOf(-1) }
    MaterialTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            when(whichScreen){
                0 -> LoginPage(whichScreen = whichScreen, onChangeScreen = { x: Int -> whichScreen = x }, viewModel = RetroViewModel())
                1 -> RetroGameApp(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x }, consoleFilter, conditionFilter,viewModel = RetroViewModel())
                2 -> BuyPage(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x }, viewModel = RetroViewModel())
                3 -> FilterPage(
                    whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x },
                    consoleFilter = consoleFilter, changeConsoleFilter = {console: String -> consoleFilter = console},
                    conditionFilter = conditionFilter, changeConditionFilter = {cond: Int -> conditionFilter = cond})
                4 -> PaymentPage(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x })
                //5 -> SignUpPage(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x }, viewModel = RetroViewModel())
                else -> {
                    println("default");
                }
            }
        }
    }
}
