package shop.ebusiness.product
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import shop.ebusiness.R

@Composable
fun ProductView() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "product.name",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.erdbeeren),
            contentDescription = "product image",
            modifier = Modifier
                .size(135.dp)
                .padding(5.dp),
            contentScale = ContentScale.Crop
        )
    }
}



@Composable
fun CreateProductImage() {
    Surface(
        modifier = Modifier
            .size(width = 250.dp, height = 200.dp) // Increase the height value to make the image larger
            .padding(4.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(0.5.dp, Color.Black),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.erdbeeren),
            contentDescription = "product image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

    // Restlicher Code der Funktion

