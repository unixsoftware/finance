/*
 * PaymentCheck screen
 */

package com.example.unix.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.R
import com.example.unix.bottom_navigation.PaymentViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//----------------------------------------------------------------
// main component PaymentCheck
@Composable
fun PaymentCheck(onDismiss: () -> Unit, navController: NavController) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { onDismiss() }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "close modal",
                        modifier = Modifier,
                        tint = Color.White
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.Share,
                        contentDescription = "share check",
                        modifier = Modifier,
                        tint = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription ="check",
                    modifier = Modifier.size(width = 500.dp, height = 500.dp)
                )
                Column(
                    modifier = Modifier
                ) {
                    PaymentCheckTitle()
                    Divider(modifier = Modifier.padding(start = 30.dp, end = 30.dp))
                    PaymentUserAndPhone()
                    PaymentCheckSum()
                    PaymentCheckDetails()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column {
                    IconButton(onClick = { navController.navigate("FarePayment") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_qr_code),
                            contentDescription = "repeat transfer",
                            modifier = Modifier.size(35.dp),
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "QR-код\nTOLEM",
                        color = Color.White,
                        fontSize = 13.sp,
                        lineHeight = 15.sp
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_block),
                            contentDescription = "cancel modal",
                            modifier = Modifier.size(35.dp),
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Отменить\n  платеж",
                        color = Color.White,
                        fontSize = 13.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}


//----------------------------------------------------------------
// component CheckTitle
@Composable
fun PaymentCheckTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 30.dp, top = 15.dp),
        verticalArrangement = Arrangement.SpaceEvenly
        //
    ) {
        Image(
            painter = painterResource(id = R.drawable.logocircle),
            contentDescription = "logo circle",
            modifier = Modifier.size(35.dp)
        )
        Text(
            text = "Общественный\nтранспорт",
            modifier = Modifier,
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.W400,
            lineHeight = 25.sp
        )
    }
}

//----------------------------------------------------------------
// component UserAndPhone
@Composable
fun PaymentUserAndPhone() {
    Row(
        modifier = Modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.tolem),
            contentDescription = null,
            modifier = Modifier.padding(start = 30.dp, top = 15.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "TOLEM. Оплата проезда по QR",
                color = Color.DarkGray,
                fontSize = 15.sp
            )
            Text(
                text = "https://id.d7.kz/bus/v8eVdpijk720..",
                color = Color.DarkGray,
                fontSize = 13.sp
            )
        }
    }
}

//----------------------------------------------------------------
// component CheckSum
@Composable
fun PaymentCheckSum() {
    Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(164, 219, 91))
                .padding(start = 19.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Перевод успешно совершен",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "70,00 \u20B8",
                color = Color.White,
                fontSize = 38.sp,
            )
        }
    }
}

//----------------------------------------------------------------
// component CheckDetails
@Composable
fun PaymentCheckDetails() {
    // Получаем текущую дату и время
    val currentDate = Date()
    // Определяем формат даты и времени
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    // Форматируем дату и время в нужный вид
    val formattedDateTime = dateFormat.format(currentDate)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "№ квитанции",
                color = Color(165, 165, 165),
                fontSize = 12.sp
            )
            Text(
                text = "18088899988",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Комиссия",
                color = Color(165, 165, 165),
                fontSize = 12.sp
            )
            Text(
                text = "0 \u20B8",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Код",
                color = Color(165, 165, 165),
                fontSize = 12.sp
            )
            Text(
                text = "https://id.d7.kz/bus\nv8eVdpijk720_2h6r\nZzyBbxVZfGiLz0jf",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Дата платежа",
                color = Color(165, 165, 165),
                fontSize = 12.sp
            )
            Text(
                text = "$formattedDateTime",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ФИО плательщика",
                color = Color(165, 165, 165),
                fontSize = 12.sp
            )
            Text(
                text = "Алимкулов Р.А",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}




//component preview
@Preview
@Composable
fun PreviewPaymentCheckDialog() {
    PaymentCheck(onDismiss = {}, navController = rememberNavController())
}