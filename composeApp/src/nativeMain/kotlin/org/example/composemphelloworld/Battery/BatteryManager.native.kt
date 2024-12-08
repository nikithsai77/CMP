package org.example.composemphelloworld.battery

actual class BatteryManager {
    actual fun getBatteryLevel(): Int {
        return 100
    }
}