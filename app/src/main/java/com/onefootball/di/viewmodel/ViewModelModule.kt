package com.onefootball.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onefootball.di.helper.ViewModelKey
import com.onefootball.ui.mynews.MyNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class ViewModelModule {

    @Binds
    @Singleton
    internal abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MyNewsViewModel::class)
    internal abstract fun myNewsViewModel(viewModel: MyNewsViewModel): ViewModel

}