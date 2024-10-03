package com.bbb.glesimageprocessor.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ImageProcessingGlViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _fps: MutableStateFlow<Int> = MutableStateFlow(0)
    val fps = _fps.asStateFlow()

    private val _processingSpeed: MutableStateFlow<List<Long>> = MutableStateFlow(listOf())
    val processingSpeed = _processingSpeed.asStateFlow()

    private val _originalImage: MutableStateFlow<ByteArray> = MutableStateFlow(ByteArray(0))
    val originalImage = _originalImage.asStateFlow()

    private var fpsTimer = System.currentTimeMillis()

    private val androidApplication: Application
        get() = getApplication()

    init {
        processingSpeed.map { it.average().toInt() }
        val stream = androidApplication.resources.openRawResource(com.bbb.hades.core.common.R.raw.sample)
        try {
            val bytes = ByteArray(stream.available())
            stream.read(bytes)

            _originalImage.update {
                bytes
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun onFrameProcessFinished(processMilliSec: Long) {
        val newList = _processingSpeed.value.toMutableList()

        val timeDiff = System.currentTimeMillis() - fpsTimer

        if (timeDiff > 1000) {
            _fps.value = newList.size
            fpsTimer = System.currentTimeMillis()
            newList.clear()
        }
        newList.add(processMilliSec)

        _processingSpeed.update {
            newList
        }
    }
}
