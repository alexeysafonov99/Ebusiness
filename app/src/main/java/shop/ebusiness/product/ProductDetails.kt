package shop.ebusiness.product

import CircleBackground
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.ebusiness.ui.theme.*
import shop.ebusiness.util.*

class ShoppingCart : ComponentActivity() {
    private lateinit var dbManager: DbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbManager = DbManager(this)
        dbManager.open()


        setContent {
            ProductDetails()
        }
    }


    @Composable
    fun ProductDetails(product: ProductList) {
        Text(text = "Product Details: ${product.name}")
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProductView(product : ProductList, content: @Composable () -> Unit) {
        EbusinessTheme {
            Scaffold(
                topBar = {
                    Surface(
                        color = Blue80,
                        contentColor = Blue80, // Set the background color here
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TopAppBar(
                            title = { Text(text = "Shopping Cart") },
                            navigationIcon = { BackIcon() },
                            actions = { ShoppingCartIcon() },
                            modifier = Modifier.background(Color(0xFF4FC0B3))
                        )
                    }
                },
                content = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Hintergrundkreise
                        CircleBackground()

                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))

                            // Produktname als Überschrift
                            Row(
                                modifier = Modifier.weight(0.5f),
                                horizontalArrangement = Arrangement.Center
                            )
                            // Anzeigen des Produktnamens
                            {
                                ShowProductName()

                            }

                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                // Anzeigen des Produktbildes
                                CreateProductImage()
                            }

                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .width(392.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(2f)
                                        .height(40.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Anzeige des Preises unter dem Bild
                                    ShowProductPrice()

                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Menge verringern
                                    DecreaseIcon()
                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    ShowAmount()

                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AddIcon(product = product)
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .width(392.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                ShowTotalAmount()

                            }


                            // Produktbeschreibung
                            Row(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .height(4.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                ShowManufacturer()
                            }

                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                DisplayDescription(product)
                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            )
        }
    }

    private @Composable
    fun ShowTotalAmount() {
        Text(
            text = "Total Amount: ${product.pr * product.pr} €",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF2A6F62)
            ),
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
    }

    // einh_anz, anz, einh, pr

    @Composable
    fun ShowManufacturer() {
        Text(
            text = product.her,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF2A6F62)
            ),
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
    }

    @Composable
    fun ShowAmount() {
        Text(text = " 100g ",
            style = MaterialTheme.typography.bodyLarge , // Set the text style to MaterialTheme typography with h6 style
            fontWeight = FontWeight.Bold, // Set the text weight to bold
            fontSize = 20.sp)
    }

    @Composable
    fun ShowProductName() {
        Text(
            text = product.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF2A6F62)
            ),
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
    }

    @Composable
    fun ShowProductPrice() {
        Text(text = product.pr.toString() + " €/kg",
            style = MaterialTheme.typography.bodyLarge , // Set the text style to MaterialTheme typography with h6 style
            fontWeight = FontWeight.Bold, // Set the text weight to bold
            fontSize = 20.sp)
    }


    @Composable
    fun ProductDetails(){
        Text(text = "Produkt Details")
    }

    val product = ProductList().apply { name = "Erdbeeren"; her = "Hersteller"; pr = 4.99; war = "Erntefrische Erdebberen in demeter Bio-Qualität. Fruchtig, Süß und saftig."; einh_anz = "100g"; anz = 1; einh = "g" }



    @Preview(showBackground = true)
    @Composable
    fun ProductPreview() {
        ProductView(product, content = { ProductDetails() })
    }




    @Composable
    fun DisplayDescription(product: ProductList) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color(0xFF348077)),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Beschreibung",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF2A6F62)
                    ),
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                Text(
                    text = product.war,
                    style = MaterialTheme.typography.bodyLarge, // Adjust the typography style as needed
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }


}