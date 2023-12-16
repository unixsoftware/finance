/*
 * TransferViewModel screen
 */

package com.example.unix.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TransferViewModel : ViewModel() {
    var moneyText by mutableStateOf("")
    var messageText by mutableStateOf("")
    var phoneText by mutableStateOf("")


    // MyBankViewModel
    var count by mutableStateOf("")
    var enteredValue by mutableStateOf("8 664,24")

    fun updateEnteredValue(newValue: String) {
        enteredValue = newValue
    }
}
