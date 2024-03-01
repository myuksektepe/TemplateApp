package kodz.org.core.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kodz.org.core.data.HttpRequestState
import kodz.org.core.data.HttpRequestType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val ktorClient: HttpClient
) : DataSource {

    suspend fun makeRequest(
        endpoint: String,
        httpRequestType: HttpRequestType
    ): Flow<HttpRequestState<String>> = flow {

        val response =
            if (httpRequestType == HttpRequestType.POST)
                ktorClient.post(endpoint) {
                    contentType(ContentType.Application.Json)
                }
            else
                ktorClient.get(endpoint) {
                    contentType(ContentType.Application.Json)
                }


        // Loading
        emit(HttpRequestState.Loading)

        // Success
        if (response.status.value in 200..299) {
            emit(HttpRequestState.Success(response.bodyAsText()))
        }

        // Error
        else {
            emit(HttpRequestState.Error(response.body()))
        }

    }.catch {
        emit(HttpRequestState.Error(it.message.toString()))
    }

}

