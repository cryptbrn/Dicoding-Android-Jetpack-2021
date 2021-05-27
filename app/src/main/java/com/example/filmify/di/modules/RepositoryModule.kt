package com.example.filmify.di.modules

import com.example.filmify.repository.Repository
import com.example.filmify.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRepository(api: ApiService): Repository {
        return Repository(api)
    }
}