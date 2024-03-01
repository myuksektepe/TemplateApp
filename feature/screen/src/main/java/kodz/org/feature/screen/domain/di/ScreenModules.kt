package kodz.org.feature.screen.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kodz.org.core.data.repository.RepositoryImpl
import kodz.org.feature.screen.domain.usecase.ScreenUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScreenModules {

    @Provides
    @Singleton
    fun provideScreenUseCase(repositoryImpl: RepositoryImpl): ScreenUseCase = ScreenUseCase(repositoryImpl)
}