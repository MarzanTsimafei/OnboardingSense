package com.example.onboardingsense.AdaptersAndViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel: ViewModel() {

    val posFrag: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>() }
}
