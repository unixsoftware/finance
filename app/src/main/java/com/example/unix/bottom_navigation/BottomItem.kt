package com.example.unix.bottom_navigation

import com.example.unix.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    object MainScreen: BottomItem(title = "Главная", R.drawable.outline_home, "MainScreen")
    object KaspiQR: BottomItem(title = "Kaspi QR", R.drawable.baseline_qr_code_scanner, "QrScreen")
    object Messages: BottomItem(title = "Сообщения", R.drawable.outline_sms, "Messages")
    object Services: BottomItem(title = "Сервисы", R.drawable.outline_menu, "Services")
}
