package kodz.org.core.base.data.http

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by Murat Yüksektepe on 13.01.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
interface HttpApiService {
    @POST
    suspend fun <R> doPostRequest(
        @Header("Authorization") token: String,
        @Url path: String,
        @Body inputModel: JsonObject
    ): Response<R>

    @GET
    suspend fun <R : Any> doGetRequest(
        @Header("Authorization") token: String,
        @Url path: String,
        @QueryMap params: Map<String, String>
    ): Response<R>
}