/*
 * FarePayment screen
 */

package com.example.unix.bottom_navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.example.unix.R
import com.example.unix.ui.theme.UnixTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//----------------------------------------------------------------
// main component FarePayment
@Composable
fun FarePayment(navController: NavController, viewModel: PaymentViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FarePaymentNavigationMenu(navController)
        PaymentQrCode()
        Route(viewModel)
        RouteDetails(navController, viewModel)
    }
}

//----------------------------------------------------------------
// component FarePaymentNavigationMenu
@Composable
fun FarePaymentNavigationMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(50.dp)
            .border(width = 2.dp, color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate("Payment") },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Icon(
                Icons.Filled.Close, contentDescription = null, tint = Color.Red,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Text(
            text = "TOLEM. Оплата проезда",
            modifier = Modifier,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

//----------------------------------------------------------------
// component PaymentQrCode
@Composable
fun PaymentQrCode() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.qrcode),
            contentDescription = null,
            modifier = Modifier.size(170.dp)
        )
        Text(
            text = "Покажите QR-код контролеру",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

//----------------------------------------------------------------
// component Route
@Composable
fun Route(viewModel: PaymentViewModel) {
    val routeText = viewModel.routeText
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(width = 1.dp, color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Маршрут $routeText", fontWeight = FontWeight.W500, fontSize = 15.sp)
    }
}

//----------------------------------------------------------------
// component RouteDetails
@Composable
fun RouteDetails(navController: NavController, viewModel: PaymentViewModel) {
    // Получаем текущую дату и время
    val currentDate = Date()
    // Определяем формат даты и времени
    val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale("ru", "RU"))
    // Форматируем дату и время в нужный вид
    val formattedDateTime = dateFormat.format(currentDate)

    //Получаем данные из viewModel
    val stateText = viewModel.stateText
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Госномер $stateText")
            Text(text = "$formattedDateTime")
            Text(text = "70 тенге")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.Share,
                contentDescription = null,
                tint = Color(0, 128, 215),
            )
            Text(text = "Поделиться", color = Color(0, 128, 215))
        }
        TextButton(
            onClick = { navController.navigate("Payment") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0, 128, 215),
                contentColor = Color.White
            )
        ) {
            Text("ВЕРНУТЬСЯ В ПЛАТЕЖИ", modifier = Modifier.padding(5.dp))
        }
    }
}


//component preview
@Preview(showBackground = true)
@Composable
fun FarePaymentPreview() {
    UnixTheme {
        FarePayment(navController = rememberNavController(), viewModel = PaymentViewModel())
    }
}