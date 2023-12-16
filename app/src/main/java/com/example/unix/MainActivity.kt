/*
 * Copyright (C) 2023 The Unix Open Source Project
 */

package com.example.unix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unix.bottom_navigation.BottomItem
import com.example.unix.bottom_navigation.FarePayment
import com.example.unix.bottom_navigation.MainScreen
import com.example.unix.bottom_navigation.Messages
import com.example.unix.bottom_navigation.Payment
import com.example.unix.bottom_navigation.PaymentViewModel
import com.example.unix.bottom_navigation.QrScreen
import com.example.unix.bottom_navigation.Services
import com.example.unix.screens.MyBank
import com.example.unix.screens.Transfer
import com.example.unix.screens.TransferCompleted
import com.example.unix.screens.TransferDetails
import com.example.unix.screens.TransferNavigation
import com.example.unix.screens.TransferViewModel
import com.example.unix.ui.theme.UnixTheme


class MainActivity : ComponentActivity() {
    private val transferViewModel: TransferViewModel by viewModels()
    private val paymentViewModel: PaymentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnixTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "MainScreen") {
                    composable("TransferNavigation") { TransferNavigation(navController) }
                    composable("Transfer") { Transfer(navController, viewModel = transferViewModel) }
                    composable("TransferDetails") { TransferDetails(navController, viewModel = transferViewModel) }
                    composable("TransferCompleted") { TransferCompleted(navController, viewModel = transferViewModel) }
                    composable("Payment") { Payment(navController, viewModel = paymentViewModel) }
                    composable("FarePayment") { FarePayment(navController, viewModel = paymentViewModel) }
                    composable("MyBank") { MyBank(navController, viewModel = transferViewModel)}

                    //for BottomNavigation
                    composable("MainScreen") { MainScreen(navController) }
                    composable("QrScreen") { QrScreen(navController) }
                    composable("Messages") { Messages(navController) }
                    composable("Services") { Services(navController) }
                }
            }
        }
    }
}





@Composable
fun MainBottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.MainScreen,
        BottomItem.KaspiQR,
        BottomItem.Messages,
        BottomItem.Services
    )
    androidx.compose.material.BottomNavigation(
        modifier = Modifier.zIndex(1f),
        backgroundColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route)},
                icon = {
                    Icon(painter = painterResource(id = item.iconId),
                        contentDescription = "Icon"
                    )
                },
                label = {
                    Text(text = item.title, fontSize = 10.sp)
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray
            )
        }
    }
}
