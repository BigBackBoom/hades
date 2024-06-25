package com.bbb.hades.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bbb.hades.R
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

    private val _imageByte: MutableStateFlow<ByteArray> = MutableStateFlow(ByteArray(0))
    val imageByte: StateFlow<ByteArray> = _imageByte.asStateFlow()

    private val _processingTime: MutableStateFlow<Long> = MutableStateFlow(0)
    val processingTime: StateFlow<Long> = _processingTime.asStateFlow()

    private val androidApplication: Application
        get() = getApplication()

    fun loadImage() {

        val stream = androidApplication.resources.openRawResource(R.raw.sample)
        try {
            val bytes = ByteArray(stream.available())
            stream.read(bytes)
            val startTime = System.currentTimeMillis() // 処理開始時間を取得
//            processImage(bytes)
            processImageJava(bytes)
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

    private fun processImageJava(bmpByteArray: ByteArray) {
        var counter = 0
        val alpha = 0.7
        val beta = 0.3

        while (counter < 1) {
            var index = 54
            while (index < bmpByteArray.size) {

                val a = bmpByteArray[index].toInt() and 0xff
                val b = bmpByteArray[index + 1].toInt() and 0xff
                val c = bmpByteArray[index + 2].toInt() and 0xff

                bmpByteArray[index] = ((alpha * a).toInt() + (beta * 0).toInt()).toByte()
                bmpByteArray[index + 1] = ((alpha * b).toInt() + (beta * 0).toInt()).toByte()
                bmpByteArray[index + 2] = ((alpha * c).toInt() + (beta * 255).toInt()).toByte()
                index += 3
            }
            counter += 1
        }

        println(counter)
    }

    private external fun processImage(bmpByteArray: ByteArray)
}