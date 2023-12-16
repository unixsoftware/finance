/*
 * Transfer screen
 */

package com.example.unix.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unix.MainBottomNavigation
import com.example.unix.R
import com.example.unix.ui.theme.UnixTheme

//--------------------------------------------------------------
// main component Transfer
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transfer(navController: NavController, viewModel: TransferViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
    ) {
        NavigationMenu(navController)
        Spacer(modifier = Modifier.height(12.dp))
        CardCount(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        TextTabs(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        MoneyTextField(viewModel)
        MessageTextField(viewModel)
        SendButton(navController = navController, viewModel)
    }
}




//--------------------------------------------------------------
// component NavigationMenu
@Composable
fun NavigationMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate("TransferNavigation") },
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
//component ImageAndText
@Composable
fun CardCount(viewModel: TransferViewModel) {
    val enteredValue = viewModel.enteredValue
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
                text = stringResource(R.string.kaspi_gold), fontSize = 15.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        Text(
            text = "$enteredValue \u20B8", fontSize = 15.sp,
        )
    }
}

//--------------------------------------------------------------
// component TextTabs
@Composable
fun TextTabs(viewModel: TransferViewModel) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("ТЕЛЕФОН", "КАРТА", "KASPI QR")
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
            0 -> PhoneTextField(viewModel)
            1 -> CardTextField()
            2 -> QrScreen()
        }
    }
}

//--------------------------------------------------------------
// component phoneTextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextField(viewModel: TransferViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newValue ->
            text = newValue
            viewModel.phoneText = newValue },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        label = { Text("Телефон получателя") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true,
        trailingIcon = { Icon(
            Icons.Filled.Person,
            "contentDescription") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            containerColor = Color.White,
            cursorColor = Color.Red,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedTrailingIconColor = Color.Red,
            unfocusedTrailingIconColor = Color.Red,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
        ),
    )
}

//--------------------------------------------------------------
// component CardTextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTextField() {
    Row(modifier = Modifier.background(color = Color.White), verticalAlignment = Alignment.CenterVertically,) {
        Image(
            painter = painterResource(id = R.drawable.ic_gold),
            contentDescription = "golden",
            modifier = Modifier
                .padding(start = 20.dp)
                .size(40.dp)
        )
        var text by rememberSaveable { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            label = { Text("Kaspi Gold получателя") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_credit_card),
                    "carTextField credit card"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Red,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedTrailingIconColor = Color.Red,
                unfocusedTrailingIconColor = Color.Red,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray
            ),
        )
    }
}

//--------------------------------------------------------------
// component QrScreen
@Composable
fun QrScreen() {
    Text(text = "camera error")
}

//----------------------------------------------------------------
// component moneyTextfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyTextField(viewModel: TransferViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newValue ->
            text = newValue
            viewModel.moneyText = newValue },
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true,
        textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.End),
        placeholder = { Text(
            "0",
            modifier = Modifier
                .padding(start = 150.dp),
            fontSize = 24.sp) },
        trailingIcon = {
            Text(
                text = "\u20B8",
                modifier = Modifier.padding(end = 156.dp),
                fontSize = 26.sp,
                color = Color.Black
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            containerColor = Color.White,
            cursorColor = Color.Red,
            focusedIndicatorColor = Color.White,
            placeholderColor = Color.Black,
            unfocusedIndicatorColor = Color.White,
        ),
    )
}

//----------------------------------------------------------------
// component MessageTextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageTextField(viewModel: TransferViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newValue ->
            text = newValue
            viewModel.messageText = newValue },
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        label = { Text("Сообщение получателю") },
        singleLine = true,
        trailingIcon = { Icon(
            Icons.Rounded.Person,
            "contentDescription",
        ) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            containerColor = Color.White,
            cursorColor = Color.Gray,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedTrailingIconColor = Color.Gray,
            unfocusedTrailingIconColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
        ),
        shape = RoundedCornerShape(10.dp),
        supportingText = {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Рахмет!",
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(3.dp),
                    fontSize = 14.sp)
                Text(text = "За обед",
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(3.dp),
                    fontSize = 14.sp)
                Text(text = "Возвращаю!",
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(3.dp),
                    fontSize = 14.sp)
            }
        }
    )
}


//----------------------------------------------------------------
// component SendButton
@Composable
fun SendButton(navController: NavController, viewModel: TransferViewModel) {
    val moneyText = viewModel.moneyText
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Комиссия 0\u20B8", color = Color.Gray, fontSize = 13.sp)
        TextButton(
            onClick = { navController.navigate("TransferDetails")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0, 128, 215),
                contentColor = Color.White
            )
        ) {
            Text("ПЕРЕВЕСТИ $moneyText \u20B8", modifier = Modifier.padding(5.dp))
        }
    }
}





//component preview
@Preview(showBackground = true)
@Composable
fun MainUnixPreviewRollerApp() {
    UnixTheme {
        Transfer(navController = rememberNavController(), viewModel = TransferViewModel())
    }
}

