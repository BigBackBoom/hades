package com.bbb.imageprocessor.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bbb.imageprocessor.AlphaBlender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ImageProcessingViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _originalImage: MutableStateFlow<ByteArray> = MutableStateFlow(ByteArray(0))

    private val _imageByte: MutableStateFlow<ByteArray> = MutableStateFlow(ByteArray(0))
    val imageByte: StateFlow<ByteArray> = _imageByte.asStateFlow()

    private val _processingTime: MutableStateFlow<Long> = MutableStateFlow(0)
    val processingTime: StateFlow<Long> = _processingTime.asStateFlow()

    private val androidApplication: Application
        get() = getApplication()

    init {
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

    fun loadImage(type: ImageProcessingType) {

        val stream = androidApplication.resources.openRawResource(com.bbb.hades.core.common.R.raw.sample)
        try {
            val bytes = ByteArray(stream.available())
            stream.read(bytes)
            val startTime = System.currentTimeMillis() // 処理開始時間を取得

            if (type == ImageProcessingType.NATIVE) {
                AlphaBlender.processImage(bytes, intArrayOf(0, 0, 255, 70))
            } else {
                AlphaBlender.processImageJava(bytes, intArrayOf(255, 0, 0, 70))
            }

            val endTime = System.currentTimeMillis()

            _imageByte.update {
                bytes
            }

            _processingTime.update {
                endTime - startTime
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    enum class ImageProcessingType {
        JAVA,
        NATIVE
    }
}