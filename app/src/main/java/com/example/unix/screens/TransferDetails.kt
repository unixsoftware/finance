/*
 * TransferDetails screen
 */

package com.example.unix.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.R



//--------------------------------------------------------------
// main component TransferDetails
@Composable
fun TransferDetails(navController: NavController, viewModel: TransferViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        TransferDetailsNavigationMenu(navController)
        Spacer(modifier = Modifier.height(30.dp))
        CardCount(viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        UserCount(viewModel)
        Spacer(modifier = Modifier.height(30.dp))
        TransferCountDetails(viewModel)
        DetailsSendButton(navController = navController, viewModel)
    }
}

//--------------------------------------------------------------
// component NavigationMenu
@Composable
fun TransferDetailsNavigationMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate("Transfer") },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Icon(
                Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Red,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Text(
            text = stringResource(R.string.title),
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

//--------------------------------------------------------------
// component UserCount
@Composable
fun UserCount(viewModel: TransferViewModel) {
    val messageText = viewModel.messageText
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(70.dp)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_gold),
                contentDescription = "gold"
            )
            Text(
                text = "$messageText", fontSize = 15.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

//--------------------------------------------------------------
// component TransferCountDetails
@Composable
fun TransferCountDetails(viewModel: TransferViewModel) {
    val moneyText = viewModel.moneyText
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(200.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            //verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Сумма перевода", fontSize = 15.sp,
                modifier = Modifier,
                color = Color.Black // Указываем цвет текста
            )
            Text(
                text = "$moneyText \u20B8", fontSize = 15.sp,
                color = Color.Black // Указываем цвет текста
            )
        }
        Divider(thickness = 0.3.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                //.height(70.dp)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Комиссия", fontSize = 15.sp,
                modifier = Modifier,
                color = Color.Black
            )
            Text(
                text = "0 \u20B8", fontSize = 15.sp,
                color = Color.Black
            )
        }
        Divider(thickness = 0.3.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                //.height(70.dp)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Сумма списания", fontSize = 15.sp,
                modifier = Modifier,
                color = Color.Black
            )
            Text(
                text = "$moneyText \u20B8", fontSize = 15.sp,
                color = Color.Black
            )
        }
    }
}


//--------------------------------------------------------------
// component DetailsSendButton
@Composable
fun DetailsSendButton(navController: NavController, viewModel: TransferViewModel) {
    val moneyText = viewModel.moneyText
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = { navController.navigate("TransferCompleted") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0, 128, 215),
                contentColor = Color.White
            )
        ) {
            Text("ПОДТВЕРДИТЬ И ПЕРЕВЕСТИ $moneyText \u20B8", modifier = Modifier.padding(5.dp))
        }
    }
}

//component preview
@Preview(showBackground = true)
@Composable
fun TransferDetailsPreview() {
    TransferDetails(navController = rememberNavController(), viewModel = TransferViewModel())
}
