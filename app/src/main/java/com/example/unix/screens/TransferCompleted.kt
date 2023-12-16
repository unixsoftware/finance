/*
 * TransferCompleted screen
 */

package com.example.unix.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.R
import com.example.unix.bottom_navigation.InterestingOffer


//----------------------------------------------------------------
// main component TransferCompleted
@Composable
fun TransferCompleted(navController: NavController, viewModel: TransferViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        NavigationTitle()
        TransferOutput(viewModel)
        CheckButton(viewModel)
        OutputUser(viewModel)
        InterestingText()
        InterestingOffer()
        Spacer(modifier = Modifier.height(40.dp))
        ReturnButton(navController)
    }
}



//----------------------------------------------------------------
// component NavigationTitle
@Composable
fun NavigationTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(50.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Переводы",
            modifier = Modifier.padding(start = 15.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
        )
    }
}

//----------------------------------------------------------------
// component TransferOutput
@Composable
fun TransferOutput(viewModel: TransferViewModel) {
    val moneyText = viewModel.moneyText
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(164, 219, 91))
            .height(140.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ваш перевод совершен.",
            modifier = Modifier.padding(start = 15.dp),
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$moneyText \u20B8",
            modifier = Modifier.padding(start = 15.dp),
            color = Color.White,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


//----------------------------------------------------------------
// component GetTicket
@Composable
fun CheckButton(viewModel: TransferViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    TextButton(
        onClick = { showDialog = true },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(3.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Blue
        ),
        border = BorderStroke(width = 1.dp, color = Color(240, 240, 240)),
        contentPadding = PaddingValues(end = 120.dp)
        ) {
        Column() {
            Text(
                "Показать квитанцию",
                modifier = Modifier,
                color = Color(104,164,199)
            )
            Text(
                "Квитанция сохранена в Истории",
                modifier = Modifier,
                color = Color(183,183,183)
            )
            if(showDialog) {
                CheckDialog(onDismiss = { showDialog = false }, viewModel = viewModel)
            }
        }
    }
}

//----------------------------------------------------------------
// component OutputUser
@Composable
fun OutputUser(viewModel: TransferViewModel) {
    val messageText = viewModel.messageText
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                "$messageText.",
                modifier = Modifier,
                color = Color.Black
            )
            Text(
                "Сохранить в частые",
                modifier = Modifier,
                color = Color(174,174,183),
                fontSize = 14.sp
            )
        }
        var checked by rememberSaveable { mutableStateOf(false) }
        Switch(
            checked = checked,
            onCheckedChange = {checked = it},
            modifier = Modifier.scale(0.5f)
        )
    }
}


//----------------------------------------------------------------
// component InterestingText
@Composable
fun InterestingText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color(240, 240, 240))
            .padding(start = 15.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ВАС МОЖЕТ ЗАИНТЕРЕСОВАТЬ",
            modifier = Modifier,
            color = Color(187,187,187),
            fontSize = 12.sp
        )
    }
}

//----------------------------------------------------------------
// component ReturnButton
@Composable
fun ReturnButton(navController: NavController) {
    TextButton(onClick = { navController.navigate("Transfer") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(3.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0,128,215),
            contentColor = Color.White
        )
    ) {
        Text("ВЕРНУТЬСЯ В ПЕРЕВОДЫ", modifier = Modifier.padding(5.dp))
    }
}

//component preview
@Preview
@Composable
fun PreviewTransfer() {
    TransferCompleted(navController = rememberNavController(), viewModel = TransferViewModel())
}