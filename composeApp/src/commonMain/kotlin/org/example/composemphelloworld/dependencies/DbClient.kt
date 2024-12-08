package org.example.composemphelloworld.dependencies

expect class DbClient {
    fun getPlatformName(): String
}