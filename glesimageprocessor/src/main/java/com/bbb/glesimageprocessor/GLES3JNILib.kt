package com.bbb.glesimageprocessor

object GLES3JNILib {

    init {
        System.loadLibrary("image-glesprocessing")
    }

    external fun init()
    external fun resize(width: Int, height: Int)
    external fun step()
}
