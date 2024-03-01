package kodz.org.core.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kodz.org.core.data.client.ktorClient
import kodz.org.core.data.datasource.DataSource
import kodz.org.core.data.datasource.RemoteDataSource
import kodz.org.core.data.repository.Repository
import kodz.org.core.data.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideKtorClient() = ktorClient

    @Singleton
    @Provides
    fun provideRemoteDataSource(): DataSource = RemoteDataSource(ktorClient)

    @Singleton
    @Provides
    fun provideRepository(
        dataSource: RemoteDataSource
    ): Repository = RepositoryImpl(dataSource)

}