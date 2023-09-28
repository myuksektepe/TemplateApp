package kodz.org.feature.dashboard.data

import kodz.org.core.base.data.http.HttpBaseRequest

class DashboardRequest(
    val version: String? = "1.01"
) : HttpBaseRequest("dashboard.json")