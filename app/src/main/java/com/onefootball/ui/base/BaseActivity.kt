package com.onefootball.ui.base

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onefootball.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<V : ViewDataBinding, M : BaseViewModel>
    : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var observedDataList: MutableList<LiveData<*>> = ArrayList()

    lateinit var binding: V
    lateinit var viewModel: M

    abstract fun layoutRes(): Int
    abstract fun viewModelClass(): Class<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.simpleName, "onCreate()")

        //INIT VIEWMODEL
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(viewModelClass())
        lifecycle.addObserver(viewModel)

        //INIT BINDING
        binding = DataBindingUtil.setContentView(this, layoutRes())
        binding.lifecycleOwner = this
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

    override fun onStart() {
        super.onStart()
        Log.d(javaClass.simpleName, "onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(javaClass.simpleName, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(javaClass.simpleName, "onDestroy()")

        stopObservingData()
        lifecycle.removeObserver(viewModel)
        binding.unbind()
    }
}