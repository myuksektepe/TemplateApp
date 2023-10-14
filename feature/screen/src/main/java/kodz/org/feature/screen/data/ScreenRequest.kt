package kodz.org.feature.screen.data

import kodz.org.core.base.data.http.HttpBaseRequest

class ScreenRequest(
    endpoint: String,
    val version: String? = "1.01"
) : HttpBaseRequest(endpoint)