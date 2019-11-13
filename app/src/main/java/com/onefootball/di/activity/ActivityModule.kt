package com.onefootball.di.activity

import com.onefootball.ui.mynews.MyNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun myNewsActivity(): MyNewsActivity

}