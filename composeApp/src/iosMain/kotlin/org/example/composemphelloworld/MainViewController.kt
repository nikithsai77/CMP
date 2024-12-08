package org.example.composemphelloworld

import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import org.example.composemphelloworld.battery.BatteryManager
import org.example.composemphelloworld.networking.InitiateHttpClient
import org.example.composemphelloworld.networking.createHttpClient

fun MainViewController() = ComposeUIViewController { App(
    dataStore = createDataStoreIos(),
    batteryManager = BatteryManager(),
    httpClient = InitiateHttpClient(createHttpClient(Darwin.create()))
) }