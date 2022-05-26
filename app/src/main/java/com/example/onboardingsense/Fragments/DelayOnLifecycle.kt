package com.example.onboardingsense.Fragments


import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.*

 object DelayOnLifecycle {
     fun View.delayOnLifecycle(
        durationInMillis: Long,
        dispatcher : CoroutineDispatcher = Dispatchers.Main,
        block: () -> Unit
    ): Job? = findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
        lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher){
            delay(durationInMillis)
            if(isActive){
                block()
            }
        }
    }
}