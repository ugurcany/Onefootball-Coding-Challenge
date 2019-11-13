package com.onefootball.di

import android.app.Application
import android.content.Context
import javax.inject.Singleton
import dagger.Binds
import dagger.Module

@Module
internal abstract class AppModule {

    @Binds
    @Singleton
    internal abstract fun appContext(app: Application): Context

}