package com.onefootball

import com.onefootball.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class OneFootballApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<OneFootballApp> {
        return DaggerAppComponent.builder()
            .app(this)
            .build()
    }
}