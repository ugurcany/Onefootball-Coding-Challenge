package com.onefootball.ui.base

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

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
    }

}