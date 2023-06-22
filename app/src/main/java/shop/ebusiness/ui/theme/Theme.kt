import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import shop.ebusiness.ui.theme.Blue40
import shop.ebusiness.ui.theme.Blue80
import shop.ebusiness.ui.theme.LightBlue40
import shop.ebusiness.ui.theme.LightBlue80
import shop.ebusiness.ui.theme.Sand40
import shop.ebusiness.ui.theme.Sand80

val EbusinessScheme = darkColorScheme(
    primary = Blue80,
    secondary = LightBlue80,
    tertiary = Sand80
)


private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = LightBlue40,
    tertiary = Sand40
)

val MaterialTheme = EbusinessScheme