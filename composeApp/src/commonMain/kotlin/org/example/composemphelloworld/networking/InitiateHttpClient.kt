package org.example.composemphelloworld.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.composemphelloworld.networking.util.NetworkError
import org.example.composemphelloworld.networking.util.Result

data class InitiateHttpClient(private val httpClient: HttpClient) {

    suspend fun getData(bind: String) : Result<String, NetworkError> {
        val response = try {
            httpClient.get(urlString = "https://www.purgomalum.com/service/json") {
                parameter("text", bind)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(error = NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(error = NetworkError.Serialization)
        }
        return when(response.status.value) {
            in 200..299 -> {
                val finalText = response.body<ResultText>()
                Result.Success(data = finalText.result)
            }
            in 500..506 -> Result.Error(NetworkError.SERVER_ERROR)
            401 -> Result.Error(NetworkError.UNAUTHORISED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}