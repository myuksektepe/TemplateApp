package kodz.org.core.base.data.http

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kodz.org.core.extension.isOnline
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Murat Yüksektepe on 13.01.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class HttpRequest @Inject constructor(
    val httpApiService: HttpApiService,
) {
    suspend inline fun <RQ : HttpBaseRequest, reified RS> postRequest(
        context: Context,
        requestModel: RQ
    ): Flow<HttpFlow<RS>> = flow {
        val gson = Gson()
        val bodyJson = gson.toJson(requestModel)
        val inputJsonObject = gson.fromJson(bodyJson, JsonObject::class.java)

        coroutineScope {
            emit(HttpFlow.Loading)
            var call: Response<*>? = null
            if (context.isOnline()) {
                try {
                    call = httpApiService.doPostRequest<RS>(
                        inputModel = inputJsonObject,
                        token = "[TOKEN]",
                        path = requestModel.endpoint
                    )
                } catch (e: Exception) {
                    emit(HttpFlow.Error(BaseError(errorMessage = e.message)))
                }

                call?.let {
                    if (it.isSuccessful) {
                        it.body()?.let { body ->
                            // Type converting
                            val type = object : TypeToken<RS>() {}.type
                            val outPutJsonObject = gson.toJsonTree(body).asJsonObject
                            val responseObject = gson.fromJson<RS>(outPutJsonObject, type)
                            emit(HttpFlow.Success(responseObject))
                        } ?: kotlin.run {
                            emit(HttpFlow.Error(BaseError(errorMessage = call.message())))
                        }
                    } else {
                        emit(HttpFlow.Error(BaseError(errorMessage = call.errorBody().toString())))
                    }
                }

            } else {
                emit(HttpFlow.Error(BaseError(errorMessage = "Internet connection error!")))
            }
        }
    }

    suspend fun <RQ : Any, RS : Any> getRequest(
        context: Context,
        path: String,
        requestModel: RQ
    ): Flow<HttpFlow<RS>> = flow {
        val params = requestModel.serializeToMap()
        coroutineScope {

            emit(HttpFlow.Loading)

            if (context.isOnline()) {
                val call = httpApiService.doGetRequest<RS>(
                    params = params,
                    token = "[TOKEN]",
                    path = path
                )
                call.run {
                    if (call.isSuccessful) {
                        body()?.let {
                            // Type converting
                            val type = object : TypeToken<RS>() {}.type
                            val outPutJsonObject = gson.toJsonTree(it).asJsonObject
                            val responseObject = gson.fromJson<RS>(outPutJsonObject, type)
                            emit(HttpFlow.Success(responseObject))
                        } ?: kotlin.run {
                            emit(HttpFlow.Error(BaseError(errorMessage = call.message())))
                        }
                    } else {
                        emit(HttpFlow.Error(BaseError(errorMessage = "The http request is not successful!")))
                    }
                }
            } else {
                emit(HttpFlow.Error(BaseError(errorMessage = "Internet connection error!")))
            }
        }
    }
}