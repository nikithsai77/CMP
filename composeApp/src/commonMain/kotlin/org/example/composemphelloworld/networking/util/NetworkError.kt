package org.example.composemphelloworld.networking.util

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    UNAUTHORISED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    PAYLOAD_TO_LARGE,
    Serialization,
    UNKNOWN
}