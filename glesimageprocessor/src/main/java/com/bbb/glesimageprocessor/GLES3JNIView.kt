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
        }

        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        }
    }
}