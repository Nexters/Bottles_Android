package com.team.bottles

import androidx.lifecycle.ViewModel
import com.team.bottles.exception.BottlesException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(

): ViewModel() {

    private val _exception = MutableSharedFlow<BottlesException>(extraBufferCapacity = 1)
    val exception = _exception.asSharedFlow()



}