package org.example.composemphelloworld.dependencies

import android.content.Context

actual class DbClient(private val context: Context) {
    actual fun getPlatformName(): String {
        return "$context , Android"
    }
}