package kodz.org.core.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json

import kodz.org.core.common.consts.BASE_URL

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
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

}