package org.example.composemphelloworld.battery

import oshi.SystemInfo
import kotlin.math.roundToInt

actual class BatteryManager {
    actual fun getBatteryLevel(): Int {
        val systemInfo = SystemInfo()
        val level = systemInfo.hardware.powerSources.firstOrNull()
        return level?.remainingCapacityPercent?.times(other = 100)?.roundToInt() ?: -1
    }
}