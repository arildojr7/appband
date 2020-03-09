package dev.arildo.appband.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val viewModelExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO + viewModelExceptionHandler

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}