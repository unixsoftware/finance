package com.example.unix.bottom_navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

//----------------------------------------------------------------
// main component MainScreen
@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        MainScreenSearch()
        InterestingOffer()
        Spacer(modifier = Modifier.height(10.dp))
        MainScreenServiceList(navController)
        Spacer(modifier = Modifier.height(10.dp))
        MainScreenLazyRow()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        MainBottomNavigation(navController = navController)
    }
}

//----------------------------------------------------------------
// component MainScreenSearch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenSearch() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, Color.LightGray),
        placeholder = {
            androidx.compose.material3.Text(
                text = "Поиск по Kaspi.kz",
                color = Color.Gray,
                fontSize = 15.sp
            )
        },
        leadingIcon = {
            Icon(
                Icons.Outlined.Search,
                "TransferHistory search icon",
                modifier = Modifier.size(27.dp),
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            cursorColor = Color.Red,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),
    )
}

//----------------------------------------------------------------
// component InterestingOffer
@Composable
fun InterestingOffer() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.action),
                    contentDescription = "action",
                    modifier = Modifier
                        .width(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(5.dp))
                androidx.compose.material3.Text(
                    text = "Все в рассрочку",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.electronic),
                    contentDescription = "electronic",
                    modifier = Modifier
                        .width(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(5.dp))
                androidx.compose.material3.Text(
                    text = "Электроника и техника",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.beauty),
                    contentDescription = "action",
                    modifier = Modifier
                        .width(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(5.dp))
                androidx.compose.material3.Text(
                    text = "Покупки в рассрочку",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.installment),
                    contentDescription = "action",
                    modifier = Modifier
                        .width(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(5.dp))
                androidx.compose.material3.Text(
                    text = "Открывайте новое",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


//--------------------------------------------------------------
// component ServicesNavigation
@Composable
fun MainScreenServiceList(navController: NavController) {
    val items = listOf(
        MainScreenServiceItem(R.drawable.baseline_qr_code_scanner, "Kaspi QR"){},
        MainScreenServiceItem(R.drawable.outline_phonelink, "Мой Банк"){navController.navigate("MyBank")},
        MainScreenServiceItem(R.drawable.outline_inventory, "Платежи"){navController.navigate("Payment")},
        MainScreenServiceItem(R.drawable.outline_repeat, "Переводы"){navController.navigate("TransferNavigation")},
        MainScreenServiceItem(R.drawable.outline_shopping_cart, "Магазин"){},
        MainScreenServiceItem(R.drawable.outline_local_mall, "Travel"){},
        MainScreenServiceItem(R.drawable.outline_leaderboard, "Госуслуги"){},
        MainScreenServiceItem(R.drawable.outline_aod, "Обьявления"){},
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.background(Color.White),
        content = {
            items(items) { serviceItem ->
                Column(
                    modifier = Modifier
                        .padding(10.dp)
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
data class MainScreenServiceItem(val icon: Int, val text: String, val onClick: () -> Unit)

//----------------------------------------------------------------
// component MainScreenLazyRow
@Composable
fun MainScreenLazyRow() {
    val items = listOf(
        Item(R.drawable.red, "Рассрочка 0-0-3", 1),
        Item(R.drawable.ic_gold, "Kaspi Gold\n для ребенка", 1),
        Item(R.drawable.deposit, "Kaspi Депозит", 2),
        Item(R.drawable.kredit, "Кредит на покупки", 2),
    )
    val row1Items = items.filter { it.row == 1 }
    val row2Items = items.filter { it.row == 2 }

    LazyRow(
        modifier = Modifier
            //.fillMaxWidth()
            .height(140.dp)
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Column(

            ) {
                row1Items.forEach { item ->
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(width = 30.dp))
        }

        item {
            Column() {
                row2Items.forEach { item ->
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                }
            }
        }
    }
}
data class Item(val icon: Int, val text: String, val row: Int)


@Composable
fun Messages(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(50.dp)
                .border(width = 0.5.dp, Color.LightGray)
                .padding(start = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Сообщения",
                fontSize = 18.sp,
                //fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painterResource(id = R.drawable.outline_sms),
                contentDescription = "Messages_icon",
                modifier = Modifier.size(100.dp),
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Здесь будут Ваши\n    сообщения",
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Войдите или зарегистрируйтесь\n  что бы получать сообщения",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color(0, 128, 215),
                    contentColor = Color.White
                )
            ) {
                androidx.compose.material3.Text("ВОЙТИ/ЗАРЕГИСТРИРОВАТЬСЯ", modifier = Modifier.padding(5.dp))
            }
        }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        MainBottomNavigation(navController = navController)
    }
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QrScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White)
                .padding(start = 15.dp, end = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "Kaspi QR",
                fontSize = 19.sp,
                fontWeight = FontWeight.W500
            )
            TextButton(
                onClick = { navController.navigate("MainScreen")},
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Icon(
                    Icons.Filled.Close, contentDescription = null, tint = Color.White
                )
            }
        }
        BottomSheetScaffold(
            backgroundColor = Color.Black,
            sheetContent = {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        text = "Оформить онлайн",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.W600
                    )
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.red),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        Text(
                            text = "Рассрочка 0-0-3",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.kredit),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(width = 10.dp))
                        Text(
                            text = "Кредит на Покупки",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                }
            }
        ) {

        }
    }
}



//component preview
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UnixTheme {
        MainScreen(navController = rememberNavController())
    }
}



