/*
 * Payment screen
 */

package com.example.unix.bottom_navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.MainBottomNavigation
import com.example.unix.R
import com.example.unix.screens.PaymentCheck
import com.example.unix.screens.TransferHistory
import com.example.unix.ui.theme.UnixTheme

@Composable
fun Payment(navController: NavController, viewModel: PaymentViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        PaymentNavigationMenu(navController)
        PaymentTextTabs(navController, viewModel)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            MainBottomNavigation(navController = navController)
      }
    }
}

//--------------------------------------------------------------
// component PaymentNavigationMenu
@Composable
fun PaymentNavigationMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate("Services") },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Red,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Text(
            text = "Платежи",
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

//--------------------------------------------------------------
// component PaymentTextTabs
@Composable
fun PaymentTextTabs(navController: NavController, viewModel: PaymentViewModel) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("ВСЕ", "МОИ ПЛАТЕЖИ", "ИСТОРИЯ")
    Column {
        TabRow(selectedTabIndex = state,
            containerColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[state]),
                    color = Color.Red
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index },
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Black
                )
            }
        }
        when(state) {
            0 -> PaymentList()
            1 -> MyPayment()
            2 -> PaymentHistory(navController, viewModel)
        }
    }
}

//--------------------------------------------------------------
// component PaymentList
@Composable
fun PaymentList() {
    TransferHistory()

    val items = listOf(
        PaymentListItem(R.drawable.outline_smartphone, "Мобильный"),
        PaymentListItem(R.drawable.outline_home, "Коммуналка и телефон"),
        PaymentListItem(R.drawable.outline_directions_transit, "Транспорт"),
        PaymentListItem(R.drawable.outline_phonelink, "Интернет и ТВ"),
        PaymentListItem(R.drawable.outline_bookmark_border, "Образование"),
        PaymentListItem(R.drawable.outline_leaderboard, "Штрафы и налоги"),
        PaymentListItem(R.drawable.outline_table_rows, "Финансовые услуги"),
        PaymentListItem(R.drawable.outline_clean_hands, "Благотворительность"),
        PaymentListItem(R.drawable.outline_spa, "Красота и здоровье"),
        PaymentListItem(R.drawable.outline_aod, "Билеты"),
        PaymentListItem(R.drawable.outline_local_mall, "Покупки и заказы"),
        PaymentListItem(R.drawable.outline_countertops, "Развлечения и отдых"),
        PaymentListItem(R.drawable.outline_inventory, "Сайты обьявлений"),
        PaymentListItem(R.drawable.outline_videogame_asset, "Игры"),
        PaymentListItem(R.drawable.outline_more_horiz, "Другое"),
    )

    LazyColumn(
        content = {
            items(items) { paymentListItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.White)
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = paymentListItem.iconResId),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = paymentListItem.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W400
                    )
                }
                Divider(thickness = 0.5.dp, color = Color.Unspecified)
            }
        }
    )
}

data class PaymentListItem(val iconResId: Int, val title: String)








//--------------------------------------------------------------
// component PaymentList
@Composable
fun MyPayment() {
    TransferHistory()
}

//--------------------------------------------------------------
// component PaymentList
@Composable
fun PaymentHistory(navController: NavController, viewModel: PaymentViewModel) {
    var selectedItem by remember { mutableStateOf<PaymentHistoryItem?>(null) }
    var isDialogOpen by remember { mutableStateOf(false) }

    var routeDialog by remember { mutableStateOf(false) }
    TransferHistory()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Настроить фильтр", fontSize = 13.sp, color = Color.Gray)
        Icon(painter = painterResource(id = R.drawable.outline_filter),
            modifier = Modifier.clickable {routeDialog = true},
            tint = Color.Red,contentDescription = null)
        if (routeDialog) {
            RouteDialog(onDismissRequest = { routeDialog = false }, viewModel)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    val items = listOf(
        PaymentHistoryItem(R.drawable.tolem, "TOLEM. Оплата проезда по QR   70,00\u20B8",
            "https://id.d7.kz/bus/v8eVdpijk720_2h6r\n ZzyBbxVZfGiLz0jfRggBvcsrhho7dZTY"),
        PaymentHistoryItem(R.drawable.tolem, "TOLEM. Оплата проезда по QR   70,00\u20B8",
            "https://id.d7.kz/bus/v8eVdpijk720_2h6r\n _qtQV7-kkdiiLz0jfRggBvcsrhho7dZTY"),
        PaymentHistoryItem(R.drawable.tolem, "TOLEM. Пополнение баланса   500,00\u20B8",
            "1178418664"),
        PaymentHistoryItem(R.drawable.beeline, "Beeline", "+7(777)769-86-64"),

        )

    LazyColumn(
        //modifier= Modifier.height(400.dp),
        content = {
            items(items) { paymentHistoryItem ->
                var isSelected = rememberUpdatedState(false)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.White)
                        .padding(start = 20.dp)
                        .clickable {
                            // Устанавливаем выбранный элемент при клике
                            selectedItem = paymentHistoryItem
                            // Открываем диалог
                            isDialogOpen = true
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Image(
                        painter = painterResource(id = paymentHistoryItem.iconResId),
                        contentDescription = null,
                        //tint = Color(122,193,71),
                        modifier = Modifier.size(24.dp)
                    )
                    Column {
                        Text(
                            text = paymentHistoryItem.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400
                        )
                        Text(
                            text = paymentHistoryItem.titleSecond,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                }
                Divider(thickness = 0.5.dp, color = Color.Unspecified)
            }
        }
    )
    if (isDialogOpen) {
        PaymentCheck(
            onDismiss = {
                // Закрытие диалога
                isDialogOpen = false
                // Сброс выбранного элемента
                selectedItem = null
            },
            navController = navController
        )
    }
}
data class PaymentHistoryItem(val iconResId: Int, val title: String, val titleSecond: String)

//--------------------------------------------------------------
// component RouteDialog
@Composable
fun RouteDialog(onDismissRequest: () -> Unit, viewModel: PaymentViewModel) {
    var route by rememberSaveable { mutableStateOf("") }

    var state by rememberSaveable { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
                //.padding(10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            TextField(
                value = route,
                onValueChange = { newValue ->
                    route = newValue
                    viewModel.routeText = newValue },
                label = { Text("Route") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified,
                    cursorColor = Color.Red,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                )
            )
            TextField(
                value = state,
                onValueChange = { newValue ->
                    state = newValue
                    viewModel.stateText = newValue },
                label = { Text("StateNumber") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Unspecified,
                    cursorColor = Color.Red,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                )
            )
            TextButton(
                onClick = { onDismissRequest() },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0, 128, 215)
                )
            ) {
                Text("ПОДТВЕРДИТЬ")
            }
        }
    }
}


//component preview
@Preview(showBackground = true)
@Composable
fun PaymentPreview() {
    UnixTheme {
        Payment(navController = rememberNavController(), viewModel = PaymentViewModel())
    }
}









