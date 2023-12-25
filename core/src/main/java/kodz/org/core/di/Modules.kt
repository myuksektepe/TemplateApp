package kodz.org.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kodz.org.core.common.CommonSettings
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun provideCommonSettings(): CommonSettings = CommonSettings()
}