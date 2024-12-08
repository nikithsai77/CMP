package org.example.composemphelloworld

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform