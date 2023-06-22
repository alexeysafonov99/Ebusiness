import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircleBackground(){
    Box(
        modifier = Modifier
            .size(200.dp)
            .offset((-190).dp, (-290).dp)
            .background(Color(0x804FC0B3), shape = CircleShape)
    )
    // Hintergrundkreise
    Box(
        modifier = Modifier
            .size(200.dp)
            .offset((-100).dp, (-350).dp)
            .background(Color(0x804FC0B3), shape = CircleShape)
    )
}
