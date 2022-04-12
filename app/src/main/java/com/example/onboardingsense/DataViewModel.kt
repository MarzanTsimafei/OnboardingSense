package com.example.onboardingsense

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DataViewModel: ViewModel() {
    val message: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }

    val posFrag: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }

}