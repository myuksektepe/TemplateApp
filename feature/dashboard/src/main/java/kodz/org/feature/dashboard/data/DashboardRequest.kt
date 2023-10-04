package kodz.org.feature.dashboard.data

import kodz.org.core.base.data.http.HttpBaseRequest

class DashboardRequest(
    endpoint: String,
    val version: String? = "1.01"
) : HttpBaseRequest(endpoint)