package com.example.application.extentions

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.activity.ComponentActivity

////////////////////////////Функция выхода из приложения
var backPressedTime : Long = 0
fun ComponentActivity.onBackPressed (activity: ComponentActivity) {
  
    if (backPressedTime + 2000 > System.currentTimeMillis()) {
        activity.finish()
    } else {
        Toast.makeText(this, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show()
    }
    backPressedTime = System.currentTimeMillis()
}

////////////////////////////Функции для проверки сетевого подключения (работает на первом экране)
fun ComponentActivity.checkConnection () : Boolean{
    var isWifiConn = false
    var isMobileConn = false
    var connectionManager : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    connectionManager.allNetworks.forEach { network ->
        connectionManager.getNetworkInfo(network)?.apply {
            if (type == ConnectivityManager.TYPE_WIFI) {
                isWifiConn = isWifiConn or isConnected
            }
            if (type == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn = isMobileConn or isConnected
            }
        }
    }
    var resultConnection = false
    if (isWifiConn || isMobileConn) {
        resultConnection =  true
    }else if (!isWifiConn && !isMobileConn) {
        resultConnection = false
    }
    return resultConnection
}

