package shop.ebusiness.product

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shop.ebusiness.ui.theme.*
import shop.ebusiness.util.*

class testing {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProductView(product: ProductList, content: @Composable () -> Unit) {
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
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .offset((-190).dp, (-290).dp)
                                .background(Color(0x804FC0B3), shape = CircleShape)
                        )

                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .offset((-100).dp, (-350).dp)
                                .background(Color(0x804FC0B3), shape = CircleShape)
                        )

                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))

                            // Produktname als Überschrift
                            Row(
                                modifier = Modifier.weight(0.5f),
                                horizontalArrangement = Arrangement.Center
                            ) {
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

                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                CreateProductImage()
                            }

                            // Reihe mit drei Spalten
                            Row(
                                modifier = Modifier.weight(1f).width(392.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(2f)
                                        .height(20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Column 1
                                    Text(text = "1")
                                    // Weitere Inhalte für Spalte 1
                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Column 1
                                    Text(text = product.pos1)
                                    // Weitere Inhalte für Spalte 1
                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Column 2
                                    Text(text = "Spalte 2")
                                    // Weitere Inhalte für Spalte 2
                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Column 3
                                    Text(text = "Spalte 3")
                                    // Weitere Inhalte für Spalte 3
                                }
                            }


                            // Produktbeschreibung
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {

                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            )
        }
    }}
