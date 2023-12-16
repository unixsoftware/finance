/*
 * TransferNavigation screen
 */

package com.example.unix.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
// main component TransferNavigation
@Composable
fun TransferNavigation(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        TransferNavigationMenu(navController)
        TransferTabs(navController)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            MainBottomNavigation(navController = navController)
        }
    }
}

//--------------------------------------------------------------
// component TransferNavigationMenu
@Composable
fun TransferNavigationMenu(navController: NavController) {
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
            text = "Переводы",
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

//--------------------------------------------------------------
// component TextTabs
@Composable
fun TransferTabs(navController: NavController) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("МОИ ПЕРЕВОДЫ", "ИСТОРИЯ")
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
            0 -> MyTransfer(navController)
            1 -> TransferHistory()
        }
    }
}

//--------------------------------------------------------------
// component MyTransfer
@Composable
fun MyTransfer(navController: NavController) {
    Spacer(modifier = Modifier.height(8.dp))
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
            contentPadding = PaddingValues(end = 120.dp)
        ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_repeat),
            contentDescription = "baseline",
            modifier = Modifier,
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(width = 13.dp))
        Text(
            text = "Между своими счетами",
            modifier = Modifier,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400
        )
    }
    Divider(thickness = 0.2.dp, color = Color.Unspecified)
    Button(
        onClick = { navController.navigate("Transfer") },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(end = 175.dp)
    ) {
        Icon(
            Icons.Outlined.Person,
            contentDescription = "person_icon",
            modifier = Modifier.size(28.dp),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(width = 13.dp))
        Column {
            Text(
                text = "Клиенту Kaspi",
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "На карту Kaspi Gold",
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        }
    }
    Divider(thickness = 0.2.dp, color = Color.Unspecified)
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(end = 140.dp)
    ) {
        Box {
            Icon(
                painter = painterResource(id = R.drawable.icons_credit_card),
                contentDescription = "baseline_credit_card",
                modifier = Modifier.size(17.dp),
                tint = Color.Red
            )
            Icon(
                painter = painterResource(id = R.drawable.icons_credit_card),
                contentDescription = "baseline_credit_card",
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 5.dp, top = 7.dp),
                tint = Color.Red
            )
        }
        Spacer(modifier = Modifier.width(width = 13.dp))
        Column {
            Text(
                text = "Карта другого банка",
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "С карты на карту",
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        }
    }
    Divider(thickness = 0.2.dp, color = Color.Unspecified)
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(end = 94.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.globe),
            contentDescription = "globe_icon",
            modifier = Modifier.size(23.dp),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(width = 13.dp))
        Column {
            Text(
                text = "Международные переводы",
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "По номеру карты или телефона",
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        }
    }
    Divider(thickness = 0.2.dp, color = Color.Unspecified)
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(1.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(end = 162.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_qr_code_scanner),
            contentDescription = "qr_code_scanner",
            modifier = Modifier.size(27.dp),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(width = 13.dp))
        Column {
            Text(
                text = "Kaspi QR",
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "Сканируйте и платите",
                modifier = Modifier,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        }
    }
}

//--------------------------------------------------------------
// component TransferHistory
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferHistory() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(240, 240, 240))
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            var text by rememberSaveable { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth(),
                    //.height(20.dp),
                placeholder = {
                    Text(
                        text = "Поиск по Kaspi.kz",
                        color = Color.Gray
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Search,
                        "TransferHistory search icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Red
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
    }
}



//component preview
@Preview(showBackground = true)
@Composable
fun PreviewTransferNavigation() {
    UnixTheme {
        TransferNavigation(navController = rememberNavController())
    }
}
