package com.onefootball.ui.base

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var disposableMap: Hashtable<String, Disposable> = Hashtable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        Log.d(javaClass.simpleName, "onCreated()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStarted() {
        Log.d(javaClass.simpleName, "onStarted()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopped() {
        Log.d(javaClass.simpleName, "onStopped()")
    }

    override fun onCleared() {
        Log.d(javaClass.simpleName, "onCleared()")
        disposeAll()
    }

    fun willDispose(disposable: Disposable, tag: String) {
        //DISPOSE EX-SAME CALL IF ANY
        disposeNow(tag)

        //ADD DISPOSABLE
        compositeDisposable.add(disposable)
        disposableMap[tag] = disposable
    }

    fun disposeNow(tag: String) {
        if (disposableMap.containsKey(tag)) {
            compositeDisposable.remove(disposableMap[tag]!!)
            disposableMap.remove(tag)
        }
    }

    private fun disposeAll() {
        compositeDisposable.clear()
        disposableMap.clear()
    }
}