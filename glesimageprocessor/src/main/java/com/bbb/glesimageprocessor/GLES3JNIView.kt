package com.bbb.glesimageprocessor

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLES3JNIView(
    context: Context,
    imageByteArray: ByteArray
): GLSurfaceView(context) {

    init {
        setEGLContextClientVersion(3)
        setRenderer(Renderer(imageByteArray))
    }

    private class Renderer(private val imageByteArray: ByteArray) : GLSurfaceView.Renderer {
        private var oldTime = System.currentTimeMillis()
        private var newTime = System.currentTimeMillis()

        private var fpsCounter = 0
        private var fpsStartCounting = System.currentTimeMillis()

        override fun onDrawFrame(gl: GL10) {
            Log.d("GLES3JNIView", "frame: ${newTime - oldTime} ms")
            oldTime = newTime
            GLES3JNILib.step(intArrayOf(0, 255, 0, 70))
            newTime = System.currentTimeMillis()
            fpsCounter++

            val elapsedTime = System.currentTimeMillis() - fpsStartCounting
            if (elapsedTime > 1000) {
                Log.d("GLES3JNIView", "fps: $fpsCounter")
                fpsStartCounting = System.currentTimeMillis()
                fpsCounter = 0
            }
        }

        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
            GLES3JNILib.resize(width, height)
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            GLES3JNILib.init(imageByteArray)
        }
    }
}