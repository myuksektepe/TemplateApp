package kodz.org.core.data.repository

import kodz.org.core.data.HttpRequestType
import kodz.org.core.data.datasource.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : Repository {

    suspend fun fetchScreenContent(endpoint: String) = dataSource.makeRequest(endpoint, HttpRequestType.GET)

}