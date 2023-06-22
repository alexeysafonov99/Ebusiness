package shop.ebusiness.util

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import shop.ebusiness.product.ProductList

class Buttons {


}@Composable
fun BackIcon(){
    Surface(
        color = Color(0xFF4FC0B3), // Set the background color here
        shape = CircleShape
    ){
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun DecreaseIcon() {
    Button(
        onClick = {},
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC0B3))
    ) {
        Icon(
            imageVector = Icons.Default.Remove,
            contentDescription = "Minus",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun AddIcon(product: ProductList) : Int {
    var anzInt = product.einh_anz.toInt()

    Button(
        onClick = {
            // Increment einh_anz based on einh value
            var anzInt = product.einh_anz.toInt()
            when (product.einh) {
                "g" -> product.einh_anz += 100
                "Stck" -> anzInt++
            }
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC0B3))
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Plus",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
    return anzInt
}


// einh_anz



@Composable
fun ShoppingCartIcon() {
    Surface(
        color = Color(0xFF4FC0B3), // Set the background color here
        shape = CircleShape
    ) {
        IconButton(
            onClick = { /* Handle shopping cart icon click here */ }
        )
        {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Shopping Cart",
                tint = MaterialTheme.colorScheme.onSurface

            )
        }
    }}