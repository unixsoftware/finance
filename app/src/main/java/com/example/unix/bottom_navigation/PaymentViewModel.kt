/*
 * PaymentViewModel screen
 */

package com.example.unix.bottom_navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PaymentViewModel : ViewModel() {
    var routeText by mutableStateOf("")
    var stateText by mutableStateOf("")
}