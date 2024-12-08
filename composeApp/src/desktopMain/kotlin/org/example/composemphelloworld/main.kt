package org.example.composemphelloworld

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.engine.okhttp.OkHttp
import org.example.composemphelloworld.battery.BatteryManager
import org.example.composemphelloworld.dataStore.DATA_STORE_FILE_NAME
import org.example.composemphelloworld.dataStore.createDataStore
import org.example.composemphelloworld.di.initKoin
import org.example.composemphelloworld.networking.InitiateHttpClient
import org.example.composemphelloworld.networking.createHttpClient

fun main() {
    initKoin()
    val prefs = createDataStore {
        DATA_STORE_FILE_NAME
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KMP",
        ) {
            App(dataStore = prefs, batteryManager = BatteryManager(), httpClient = InitiateHttpClient(createHttpClient(OkHttp.create())))
        }
    }
}