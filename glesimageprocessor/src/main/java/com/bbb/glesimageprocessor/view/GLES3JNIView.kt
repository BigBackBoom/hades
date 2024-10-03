package com.bbb.glesimageprocessor.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import com.bbb.glesimageprocessor.GLES3JNILib
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLES3JNIView(
    context: Context,
    imageByteArray: ByteArray,
    onFrameProcessFinished: (Long) -> Unit = {}
): GLSurfaceView(context) {

    init {
        setEGLContextClientVersion(3)
        setRenderer(Renderer(imageByteArray, onFrameProcessFinished))
    }

    private class Renderer(
        private val imageByteArray: ByteArray,
        private val onFrameProcessFinished: (Long) -> Unit = {}
    ) : GLSurfaceView.Renderer {
        private var oldTime = System.currentTimeMillis()
        private var newTime = System.currentTimeMillis()

        override fun onDrawFrame(gl: GL10) {
            oldTime = newTime
            GLES3JNILib.step(intArrayOf(0, 255, 0, 70))
            newTime = System.currentTimeMillis()
            onFrameProcessFinished(newTime - oldTime)
        }

        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
            GLES3JNILib.resize(width, height)
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            GLES3JNILib.init(imageByteArray)
        }
    }
}