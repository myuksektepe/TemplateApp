package kodz.org.core.data.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import kodz.org.core.domain.consts.BASE_URL

val ktorClient = HttpClient(CIO) {

    defaultRequest {
        header("KEY", "VALUES")
        url(BASE_URL)
    }

    install(ContentNegotiation) {
        json()
        gson()
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("KTOR", message)
            }
        }
        level = LogLevel.ALL
    }

}