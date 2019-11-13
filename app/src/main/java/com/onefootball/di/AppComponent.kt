package com.onefootball.di

import android.app.Application
import com.onefootball.OneFootballApp
import com.onefootball.di.activity.ActivityModule
import com.onefootball.di.repository.RepositoryModule
import com.onefootball.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    ViewModelModule::class,
    RepositoryModule::class
])
interface AppComponent : AndroidInjector<OneFootballApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): Builder

        fun build(): AppComponent
    }
}