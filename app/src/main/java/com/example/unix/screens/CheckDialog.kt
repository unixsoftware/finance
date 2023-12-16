/*
 * CheckDialog screen
 */

package com.example.unix.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.unix.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//----------------------------------------------------------------
// main component CheckDialog
@Composable
fun CheckDialog(onDismiss: () -> Unit, viewModel: TransferViewModel) {
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
                    CheckTitle()
                    Divider(modifier = Modifier.padding(start = 30.dp, end = 30.dp))
                    UserAndPhone(viewModel)
                    CheckSum(viewModel)
                    CheckDetails()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    IconButton(onClick = { onDismiss() }) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "repeat transfer",
                            modifier = Modifier,
                            tint = Color.White
                        )
                    }
                    Text(text = "Повторить\n  перевод", color = Color.White, fontSize = 13.sp)
                }
                Column {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_block),
                            contentDescription = "cancel modal",
                            modifier = Modifier,
                            tint = Color.White
                        )
                    }
                    Text(text = "Запросить\n  отмену", color = Color.White, fontSize = 13.sp)
                }
                Column {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.TwoTone.Star,
                            contentDescription = "star modal",
                            modifier = Modifier,
                            tint = Color.White
                        )
                    }
                    Text(text = "Сохранить\n в Частые", color = Color.White, fontSize = 13.sp)
                }
            }
        }
    }
}


//----------------------------------------------------------------
// component CheckTitle
@Composable
fun CheckTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 30.dp, top = 15.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.logocircle),
            contentDescription = "logo circle",
            modifier = Modifier.size(35.dp)
        )
        Text(
            text = "Перевод\nклиенту Kaspi",
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
fun UserAndPhone(viewModel: TransferViewModel) {
    val messageText = viewModel.messageText
    val phoneText = viewModel.phoneText
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(start = 30.dp),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "$messageText.",
            color = Color.DarkGray
        )
        Text(
            text = "$phoneText",
            color = Color.DarkGray
        )
    }
}

//----------------------------------------------------------------
// component CheckSum
@Composable
fun CheckSum(viewModel: TransferViewModel) {
    val moneyText = viewModel.moneyText
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
                text = "$moneyText \u20B8",
                color = Color.White,
                fontSize = 38.sp,
            )
        }
    }
}

//----------------------------------------------------------------
// component CheckDetails
@Composable
fun CheckDetails() {
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
                color = Color(165,165,165),
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
                text = "Дата и время",
                color = Color(165,165,165),
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
                text = "Комиссия",
                color = Color(165,165,165),
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Отправитель",
                color = Color(165,165,165),
                fontSize = 12.sp
            )
            Text(
                text = "Алимкулов Р.А",
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
                text = "Откуда",
                color = Color(165,165,165),
                fontSize = 12.sp
            )
            Text(
                text = "Kaspi Gold",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Divider()
    }
}


//component preview
@Preview
@Composable
fun PreviewCheckDialog() {
    CheckDialog(onDismiss = {}, viewModel = TransferViewModel())
}