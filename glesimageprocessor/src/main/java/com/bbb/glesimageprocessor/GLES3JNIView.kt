package com.bbb.glesimageprocessor

import android.content.Context
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLES3JNIView(
    context: Context
): GLSurfaceView(context) {

    init {
        setEGLContextClientVersion(3)
        setRenderer(Renderer())
    }

    private class Renderer : GLSurfaceView.Renderer {
        override fun onDrawFrame(gl: GL10) {
            GLES3JNILib.step()
        }

        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
            GLES3JNILib.resize(width, height)
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            GLES3JNILib.init()
        }
    }
}