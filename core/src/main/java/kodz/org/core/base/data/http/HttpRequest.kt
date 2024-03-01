package kodz.org.core.base.data.http

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kodz.org.core.common.AppLog
import kodz.org.core.common.enums.CommonIcons
import kodz.org.core.common.enums.CommonLottie
import kodz.org.core.extension.isOnline
import kodz.org.core.model.ButtonModel
import kodz.org.core.model.DialogBox
import kodz.org.core.model.ErrorModel
import kodz.org.core.model.ErrorType
import kodz.org.core.model.EventTypeCode
import kodz.org.core.model.LottieModel
import kodz.org.core.model.ScreenModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
            if (context.isOnline()) {
                try {
                    AppLog("İsteği gönderildi.")
                    httpApiService.doPostRequest<RS>(
                        inputModel = inputJsonObject,
                        token = "[TOKEN]",
                        path = requestModel.endpoint
                    ).let {
                        if (it.isSuccessful) {
                            it.body()?.let { body ->
                                AppLog("Başarılı cevap alındı.")

                                // Type converting
                                val type = object : TypeToken<RS>() {}.type
                                val outPutJsonObject = gson.toJsonTree(body).asJsonObject
                                val responseObject = gson.fromJson<RS>(outPutJsonObject.toString(), type)
                                (responseObject as? ScreenModel)?.let { screenModel ->
                                    if (screenModel.error?.type == ErrorType.BLOCKER) {
                                        emit(HttpFlow.Error(screenModel.error))
                                        return@coroutineScope
                                    }
                                }
                                emit(HttpFlow.Success(responseObject))
                            } ?: kotlin.run {
                                emit(HttpFlow.Error(it.message().prepareUnCancelableError()))
                            }
                        } else {
                            emit(HttpFlow.Error(it.errorBody().toString().prepareUnCancelableError()))
                        }
                    }
                } catch (e: Exception) {
                    emit(HttpFlow.Error(e.message.toString().prepareUnCancelableError()))
                } catch (e: IllegalStateException) {
                    emit(HttpFlow.Error(e.message.toString().prepareUnCancelableError()))
                }
            } else {
                emit(HttpFlow.Error("Lütfen internet bağlantınızı kontrol edin.".prepareUnCancelableError()))
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
                httpApiService.doGetRequest<RS>(
                    params = params,
                    token = "[TOKEN]",
                    path = path
                ).let {
                    if (it.isSuccessful) {
                        it.body()?.let { body ->
                            // Type converting
                            val type = object : TypeToken<RS>() {}.type
                            val outPutJsonObject = gson.toJsonTree(body).asJsonObject
                            val responseObject = gson.fromJson<RS>(outPutJsonObject, type)
                            emit(HttpFlow.Success(responseObject))
                        } ?: kotlin.run {
                            emit(HttpFlow.Error(it.message().prepareUnCancelableError()))
                        }
                    } else {
                        emit(HttpFlow.Error(it.errorBody().toString().prepareUnCancelableError()))
                    }
                }
            } else {
                emit(HttpFlow.Error("Lütfen internet bağlantınızı kontrol edin.".prepareUnCancelableError()))
            }
        }
    }

    fun String.prepareUnCancelableError() = ErrorModel(
        type = ErrorType.BLOCKER,
        dialogBox = DialogBox(
            title = "Beklenmedik Bir Hata Oluştu",
            description = this,
            lottie = LottieModel(CommonLottie.ERROR),
            primaryButton = ButtonModel(
                text = "Geri Dön",
                textColor = null,
                backgroundColor = "#F44336",
                icon = CommonIcons.GO_BACK,
                eventType = EventTypeCode.CLOSE_THE_SCREEN
            ),
            secondaryButton = ButtonModel(
                text = "Yeniden Dene",
                icon = CommonIcons.REFRESH,
                textColor = "#009688",
                backgroundColor = "#ffffff",
                eventType = EventTypeCode.RETRY_LAST_ACTION
            )
        )
    )
}