package com.example.onboardingsense.AdaptersAndViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class DataViewModel: ViewModel() {

    val posFrag: MutableSharedFlow<Int> by lazy {
        MutableSharedFlow(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = BufferOverflow.SUSPEND
        )
    }
}