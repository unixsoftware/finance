/*
 * MyBank screen
 */


package com.example.unix.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.MainBottomNavigation
import com.example.unix.R
import com.example.unix.ui.theme.UnixTheme

//--------------------------------------------------------------
// main component MyBank
@Composable
fun MyBank(navController: NavController, viewModel: TransferViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        MyBankNavigationMenu(navController)
        Spacer(modifier = Modifier.height(12.dp))
        MyBankCardCount(viewModel)
        Divider(thickness = 0.3.dp)
        CardChield(viewModel, labelText = "Оформить Kaspi Gold для ребенка")
        Spacer(modifier = Modifier.height(10.dp))
        RedCard()
        Spacer(modifier = Modifier.height(10.dp))
        CardChield(viewModel, labelText = "Открыть Kaspi Депозит", handleClick = false)
        Spacer(modifier = Modifier.height(10.dp))
        CardChield(viewModel, labelText = "Оформить кредит или рассрочку", handleClick = false)
        Spacer(modifier = Modifier.height(8.dp))
        CardBonus()
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            MainBottomNavigation(navController = navController)
        }
    }
}

//--------------------------------------------------------------
// component MyBankNavigationMenu
@Composable
fun MyBankNavigationMenu(navController: NavController) {
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
            text = "Мой банк",
            modifier = Modifier,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
        )
    }
}

//--------------------------------------------------------------
// component MyBankCardCount
@Composable
fun MyBankCardCount(viewModel: TransferViewModel) {
    val enteredValue = viewModel.enteredValue
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(70.dp)
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_gold),
                contentDescription = "gold",
                modifier = Modifier.size(40.dp),
            )
            Column {
                Text(
                    text = stringResource(R.string.kaspi_gold), fontSize = 16.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text(
                    text = "*6486", fontSize = 12.sp,
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.Gray
                )
            }
        }
        Text(
            text = "$enteredValue \u20B8", fontSize = 15.sp,
        )
    }
}

//--------------------------------------------------------------
// component CardChield
@Composable
fun CardChield(
    viewModel: TransferViewModel,
    labelText: String,
    handleClick: Boolean = true
) {
    // Состояние для отслеживания видимости диалога
    var showDialog by remember { mutableStateOf(false) }

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
            Icon(
                Icons.Filled.Add, contentDescription = null, tint = Color(0, 137, 208),
                modifier = Modifier
                    .width(40.dp)
                    .height(30.dp)
                    .border(
                        BorderStroke(1.dp, color = Color(219, 219, 219)),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(2.dp)
                    .clickable(onClick = {
                        if (handleClick) {
                            showDialog = true
                        }
                    }),
            )
            Text(
                text = labelText, fontSize = 13.sp,
                modifier = Modifier.padding(start = 20.dp),
                color = Color(0, 137, 208)
            )
        }
    }
    // Проверка для отображения диалога
    if (showDialog) {
        MyBankDialog(
            onDismissRequest = { showDialog = false },
            onConfirmClick = {
                // Обработка подтверждения
                showDialog = false
            },
            viewModel
        )
    }
}



//--------------------------------------------------------------
// component MyBankDialog
@Composable
fun MyBankDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    viewModel: TransferViewModel
) {
    var count by rememberSaveable { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            //shape = RoundedCornerShape(10.dp),
        ) {
            TextField(
                value = count,
                onValueChange = { newValue ->
                    count = newValue
                    viewModel.updateEnteredValue(newValue)
                },
                label = { Text("Count") },
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
                onClick = {
                    onConfirmClick()
                    viewModel.count = count
                },
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(6.dp),
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


//--------------------------------------------------------------
// component RedCard
@Composable
fun RedCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(70.dp)
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.red),
                contentDescription = "gold",
                modifier = Modifier.size(40.dp),
            )
            Column {
                Text(
                    text = stringResource(R.string.red_card), fontSize = 16.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text(
                    text = stringResource(R.string.credit), fontSize = 12.sp,
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.Gray
                )
            }
        }
        Text(
            text = "100 000 \u20B8", fontSize = 15.sp,
        )
    }
}


//--------------------------------------------------------------
// component CardBonus
@Composable
fun CardBonus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(70.dp)
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .width(40.dp).height(30.dp)
                    .background(Color(88, 160, 0), shape = RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Б", fontSize = 20.sp, fontWeight = W500,
                    color = Color.White
                )
            }
            Text(
                text = "Kaspi Бонус", fontSize = 16.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        Text(
            text = "22,70 \u0042", fontSize = 15.sp,
        )
    }
}



//component preview
@Preview(showBackground = true)
@Composable
fun MyBankPreview() {
    UnixTheme {
        MyBank(navController = rememberNavController(), viewModel = TransferViewModel())
    }
}







