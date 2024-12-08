package org.example.composemphelloworld.dependencies

actual class DbClient {
    actual fun getPlatformName(): String {
        return "IOS"
    }
}