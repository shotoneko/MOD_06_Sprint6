package modulo_06.sprint.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import modulo_06.sprint.repository.Repository
import modulo_06.sprint.repository.RepositoryImp

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun repository(repositoryImp: RepositoryImp): Repository
}