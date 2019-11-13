package com.onefootball.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onefootball.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<V : ViewDataBinding, M : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: V
    lateinit var viewModel: M

    private var observedDataList: MutableList<LiveData<*>> = ArrayList()

    abstract fun layoutRes(): Int
    abstract fun viewModelClass(): Class<M>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, layoutRes());

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(viewModelClass())

        lifecycle.addObserver(viewModel)
    }

    override fun onDestroy() {
        stopObservingData()
        lifecycle.removeObserver(viewModel)
        binding.unbind()
        super.onDestroy()
    }

    fun <D> observe(liveData: LiveData<D>, observer: Observer<D>) {
        observedDataList.add(liveData)
        liveData.observe(this, observer)
    }

    private fun stopObservingData() {
        val iterator = observedDataList.iterator()
        while (iterator.hasNext()) {
            val liveData = iterator.next()
            liveData.removeObservers(this)
            iterator.remove()
        }
    }
}