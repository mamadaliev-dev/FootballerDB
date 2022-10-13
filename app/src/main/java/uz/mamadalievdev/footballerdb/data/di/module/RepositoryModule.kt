package uz.mamadalievdev.footballerdb.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.mamadalievdev.footballerdb.data.base.BaseRepositoryImpl
import uz.mamadalievdev.footballerdb.data.base.BaseService
import uz.mamadalievdev.footballerdb.domain.BaseRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideBaseRepository(baseService: BaseService): BaseRepository {
        return BaseRepositoryImpl(baseService)
    }

}