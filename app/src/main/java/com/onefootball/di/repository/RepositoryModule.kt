package com.onefootball.di.repository

import com.onefootball.repository.news.INewsRepository
import com.onefootball.repository.news.NewsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun newsRepository(repository: NewsRepository): INewsRepository

}