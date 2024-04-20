import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.woof.ui.theme.FunctionalGreen
import com.example.woof.ui.theme.FunctionalGrey
import com.example.woof.ui.theme.FunctionalRed
import com.example.woof.ui.theme.Lavender4
import com.example.woof.ui.theme.Lavender6
import com.example.woof.ui.theme.Shadow4

private val LightColorScheme = lightColorScheme(
    primary = FunctionalGreen,
    onPrimary = FunctionalRed,
    primaryContainer = FunctionalGrey,

)
private val DarkColorScheme = darkColorScheme(
    primary = Lavender6,
    onPrimary = Lavender4,
    primaryContainer = Shadow4,

)

@Composable
fun RetroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (!darkTheme) {
            LightColorScheme
        } else {
            DarkColorScheme
        }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}