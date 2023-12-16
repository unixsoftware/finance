/*
 * Services screen
 */

package com.example.unix.bottom_navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.MainBottomNavigation
import com.example.unix.R
import com.example.unix.ui.theme.UnixTheme

@Composable
fun Services(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        ServicesNavigation()
        RegistrationButton()
        Spacer(modifier = Modifier.height(8.dp))
        ServiceList(navController)
        Spacer(modifier = Modifier.height(8.dp))
        PartnerService()
        Spacer(modifier = Modifier.height(8.dp))
        InterestingOffer()
        // bottom navigation
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            MainBottomNavigation(navController = navController)
        }
    }
}

//--------------------------------------------------------------
// component ServicesNavigation
@Composable
fun ServicesNavigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(50.dp)
            .border(width = 0.5.dp, Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        androidx.compose.material3.Text(
            text = "Сервисы",
            modifier = Modifier.padding(start = 15.dp),
            fontSize = 19.sp,
            fontWeight = FontWeight.W500,
        )
        Row(
            modifier = Modifier.padding(end = 15.dp)
        ) {
            TextButton(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Каз")
            }
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier,//.width(50.dp).height(40.dp),
                shape = RoundedCornerShape(40.dp),
                //border = BorderStroke(2.dp, Color.Red),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.White),
                ) {
                Text(text = "Рус", color = Color.Red)
            }
        }
    }
}

//--------------------------------------------------------------
// component ServicesNavigation
@Composable
fun RegistrationButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(end = 150.dp)
    ) {
        Icon(
            Icons.Filled.Person,
            contentDescription = "baseline",
            modifier = Modifier.size(35.dp),
            tint = Color.LightGray
        )
        Spacer(modifier = Modifier.width(width = 13.dp))
        androidx.compose.material3.Text(
            text = "Вход/Регистрация",
            modifier = Modifier,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400
        )
    }
}

//--------------------------------------------------------------
// component ServicesNavigation
@Composable
fun ServiceList(navController: NavController) {
    val items = listOf(
        ServiceItem(R.drawable.outline_shopping_cart, "Магазин"){},
        ServiceItem(R.drawable.outline_phonelink, "Мой Банк"){navController.navigate("MyBank")},
        ServiceItem(R.drawable.outline_inventory, "Платежи"){navController.navigate("Payment")},
        ServiceItem(R.drawable.outline_repeat, "Переводы"){navController.navigate("TransferNavigation")},
        ServiceItem(R.drawable.outline_countertops, "Акции"){},
        ServiceItem(R.drawable.outline_local_mall, "Travel"){},
        ServiceItem(R.drawable.outline_leaderboard, "Госуслуги"){},
        ServiceItem(R.drawable.outline_aod, "Обьявления"){},
        ServiceItem(R.drawable.outline_person_outline, "Гид"){},
        ServiceItem(R.drawable.outline_location, "Kaspi Maps"){},
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.background(Color.White),
        content = {
            items(items) { serviceItem ->
                Column(
                    modifier = Modifier.padding(8.dp)
                        .clickable { serviceItem.onClick.invoke() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = serviceItem.icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(248,65,50)
                    )
                    Text(
                        text = serviceItem.text,
                        modifier = Modifier.padding(top = 4.dp),
                        fontSize = 12.sp
                    )
                }
            }
        }
    )
}
data class ServiceItem(val icon: Int, val text: String, val onClick: () -> Unit)

//--------------------------------------------------------------
// component ServicesNavigation
@Composable
fun PartnerService() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "Партнерские сервисы",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            shape = RoundedCornerShape(1.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(end = 170.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.chocofood),
                contentDescription = "chocofood",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                androidx.compose.material3.Text(
                    text = "CHOCOFOOD",
                    modifier = Modifier,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400
                )
                androidx.compose.material3.Text(
                    text = "Сервис доставки еды",
                    modifier = Modifier,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Gray
                )
            }
        }
    }
}


//component preview
@Preview(showBackground = true)
@Composable
fun ServicesPreview() {
    UnixTheme {
        Services(navController = rememberNavController())
    }
}

