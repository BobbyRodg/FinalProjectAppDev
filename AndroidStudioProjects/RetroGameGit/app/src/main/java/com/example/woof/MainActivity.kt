package com.example.woof

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.Game
import com.example.woof.data.games
import com.example.woof.ui.theme.StoreTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var whichScreen by remember { mutableStateOf(1) }
            var consoleFilter by remember { mutableStateOf("all") }
            var conditionFilter by remember { mutableStateOf(-1) }
            StoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    when(whichScreen){
                        0 -> LoginScreen(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x })
                        1 -> RetroGameApp(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x }, consoleFilter, conditionFilter)
                        2 -> BuyPage(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x })
                        3 -> FilterPage(
                            whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x },
                            consoleFilter = consoleFilter, changeConsoleFilter = {console: String -> consoleFilter = console},
                            conditionFilter = conditionFilter, changeConditionFilter = {cond: Int -> conditionFilter = cond})
                        4 -> PaymentPage(whichScreen = whichScreen, onChangeScreen = {x: Int -> whichScreen = x })
                        else -> {
                            println("default");
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(whichScreen: Int, onChangeScreen: (Int) -> Unit) {
    var user by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue(""))}

    Column(
        //modifier = Modifier.fillMaxSize()
    ){
        Text(text="Login to your account")
        OutlinedTextField( user,
            onValueChange = { newText ->
                user = newText
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary))

        OutlinedTextField( pass,
            onValueChange = { newText ->
                pass = newText
            },
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary))

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = { onChangeScreen(1) }, modifier = Modifier.padding(30.dp)) {
                Text(text="Log in")
            }
        }
    }
}

@Composable
fun FilterPage(whichScreen: Int, onChangeScreen: (Int) -> Unit,
               consoleFilter: String, changeConsoleFilter: (String) -> Unit,
               conditionFilter: Int, changeConditionFilter: (Int) -> Unit
) {

    val conditionMap = mapOf<Int, String>(
        4 to "New",
        3 to "Opened, Like New",
        2 to "Very Good",
        1 to "Good",
        0 to "For Parts. Not operational",
        -1 to "All",
    )

    val consoles: List<String> = listOf("All") + games.map { stringResource(it.console) }.toList().distinct()

    val conditions: List<String> = listOf("All") + games.map { conditionMap[it.condition] ?: "null" }.toList().distinct()


    Column(){
        Row(){
            Text(text="Console")
            ConsoleDropdownMenuBox(consoles, consoleFilter, changeConsoleFilter)
        }
        Row(){
            Text(text="Condition")
            ConditionDropdownMenuBox(conditions, conditionFilter, changeConditionFilter)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = { onChangeScreen(1) }, modifier = Modifier.padding(30.dp)) {
                Text(text="Accept")
            }
        }

    }

}

@Composable
fun BuyPage(whichScreen: Int, onChangeScreen: (Int) -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(
                onClick = { onChangeScreen(0) }, // Navigate back to the previous screen
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back button"
                )
            }
            Image(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small)),
                painter = painterResource(R.drawable.logo),
                contentDescription = null
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
            ){
                Text(text = "lorem ipsum")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text="price")
                Button(onClick = { onChangeScreen(4) }){
                    Text("BUY NOW")
                }
            }
        }
    }
}


@Composable
fun PaymentPage(whichScreen: Int, onChangeScreen: (Int) -> Unit) {
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
            TextField(
                value = "",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Last Name", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = "",
                onValueChange = {},
                readOnly = true,
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
                TextField(
                    value = "25/01", // Replace with actual value
                    onValueChange = {},
                    readOnly = true,
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
                TextField(
                    value = "United Kingdom", // Replace with actual value
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = "350000", // Replace with actual value
                    onValueChange = {},
                    readOnly = true,
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



@Composable
fun CardNumberTextField() {
    var cardNumber by remember {mutableStateOf("")}

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

@Composable
fun ConsoleDropdownMenuBox(listContents: List<String>, consoleFilter: String, changeConsoleFilter: (String) -> Unit) {
    val context = LocalContext.current
    //val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listContents[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listContents.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            changeConsoleFilter(selectedText)
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}@Composable
fun ConditionDropdownMenuBox(listContents: List<String>, conditionFilter: Int, changeConditionFilter: (Int) -> Unit) {
    val conditionMapReverse = mapOf<String, Int>(
        "New" to 4,
        "Opened, Like New" to 3,
        "Very Good" to 2,
        "Good" to 1,
        "For Parts. Not operational" to 0,
        "All" to -1,
    )
    val context = LocalContext.current
    //val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listContents[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listContents.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            changeConditionFilter(conditionMapReverse[selectedText] ?: -1)
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}


/**
 * Composable that displays an app bar and a list of dogs.
 */
@Composable
fun RetroGameApp(
    whichScreen: Int,
    onChangeScreen: (Int) -> Unit,
    consoleFilter: String,
    conditionFilter: Int
) {

    var filteredGames = games.filter { (consoleFilter == stringResource(id = it.console) || consoleFilter == "all" ) && (conditionFilter == it.condition || conditionFilter == -1)}

    Scaffold(
        topBar = {
            StoreTopAppBar(whichScreen , onChangeScreen)
        }
    ) { it ->

        LazyVerticalGrid(
            GridCells.Fixed(2),
            contentPadding = it,
        ) {
            items(filteredGames, span = { game ->
                val span = if (game.selected == true ){
                    maxCurrentLineSpan
                }
                else{
                    1
                }
                GridItemSpan(span)
            }
            ) {
                GameCard(
                    game = it,
                    onChangeScreen = onChangeScreen, // Pass onChangeScreen
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}


/**
 * Composable that displays a list item containing a game icon and their information.
 *
 * @param game contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable
fun GameCard(
    game: Game,
    onChangeScreen: (Int) -> Unit, // Add onChangeScreen parameter
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
            ){
                GameIcon(game.imageResourceId)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                GameInformation(game.name, game.condition, game.releaseYear)
                Spacer(Modifier.weight(1f))
                GameItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                game.selected = true
                CardDropdownDescription(
                    game.shortDescriptions, game.releaseYear, onChangeScreen, // Pass onChangeScreen
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
            else{
                game.selected = false
            }
        }
    }
}


/**
 * Composable that displays a button that is clickable and displays an expand more or an expand less
 * icon.
 *
 * @param expanded represents whether the expand more or expand less icon is visible
 * @param onClick is the action that happens when the button is clicked
 * @param modifier modifiers to set to this composable
 */
@Composable
private fun GameItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

/**
 * Composable that displays a Top Bar with an icon and text.
 *
 * @param modifier modifiers to set to this composable
 */
@Composable
fun StoreTopAppBar(whichScreen: Int, onChangeScreen: (Int) -> Unit, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }


        },
        modifier = modifier
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(onClick = { onChangeScreen(0) }) {
            Text(text="Log in")
        }
        Button(onClick = { onChangeScreen(3) }) {
            Text(text="Filter")
        }

    }

}

/**
 * Composable that displays a photo of a dog.
 *
 * @param gameIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
fun GameIcon(
    @DrawableRes gameIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .aspectRatio(1F)
            .fillMaxSize()
            .size(dimensionResource(R.dimen.image_size))
        //.clip(MaterialTheme.shapes.small)
        //.padding(dimensionResource(R.dimen.padding_small))
        ,
        contentScale = ContentScale.Fit,
        painter = painterResource(gameIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

/**
 * Composable that displays a game's name and release year.
 *
 * @param gameName is the resource ID for the string of the dog's name
 * @param releaseDate is the Int that represents the game's release date
 * @param modifier modifiers to set to this composable
 */
@Composable
fun GameInformation(
    @StringRes gameName: Int,
    conditionNum: Int,
    releaseYear: Int,
    modifier: Modifier = Modifier
) {
    val conditionMap = mapOf<Int, String>(
        4 to "New",
        3 to "Opened, Like New",
        2 to "Very Good",
        1 to "Good",
        0 to "For Parts. Not operational",
    )

    Column(modifier = modifier) {
        Text(
            text = stringResource(gameName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.condition, conditionMap[conditionNum] ?: "Missing"),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

/**
 * Composable that displays a dog's hobbies.
 *
 * @param shortDescription is the resource ID for the text string of the description to display
 * @param modifier modifiers to set to this composable
 */
@Composable
fun CardDropdownDescription(
    @StringRes shortDescription: Int,
    releaseYear: Int,
    onChangeScreen: (Int) -> Unit, // Add this parameter
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.release_year, releaseYear),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(shortDescription),
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = { onChangeScreen(2) }, // Navigate to the Buy Page (page 2)
            modifier = Modifier.padding(vertical = 8.dp) // Add some padding for better spacing
        ) {
            Text("More Info")
        }
    }
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.

@Preview
@Composable
fun StorePreview() {
StoreTheme(darkTheme = false) {
RetroGameApp(whichScreen: Int, {})
}
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
*/
@Preview
@Composable
fun StoreDarkThemePreview() {
StoreTheme(darkTheme = true) {
RetroGameApp({})
}
}


// Parent Composable with state
@Composable
fun MyApp() {
var count by remember { mutableStateOf(0) }

// Call child composable with state and update function
Counter(count = count, onIncrement = { count++ })
}

// Stateless child composable
@Composable
fun Counter(count: Int, onIncrement: () -> Unit) {
Button(onClick = onIncrement) {
Text(text = "Count: $count")
}
}

 */