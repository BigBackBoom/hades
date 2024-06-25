package com.bbb.imageprocessor


object AlphaBlender {

    const val RED = 0
    const val GREEN = 1
    const val BLUE = 2
    const val ALPHA = 3

    fun processImageJava(bmpByteArray: ByteArray, rgbArray: IntArray) {
        val alpha = rgbArray[ALPHA] * 0.01
        val beta = 1.0f - (rgbArray[ALPHA] * 0.01)


        var index = 54
        while (index < bmpByteArray.size) {

            val a = bmpByteArray[index].toInt() and 0xff
            val b = bmpByteArray[index + 1].toInt() and 0xff
            val c = bmpByteArray[index + 2].toInt() and 0xff

            bmpByteArray[index] = ((alpha * a).toInt() + (beta * rgbArray[BLUE]).toInt()).toByte()
            bmpByteArray[index + 1] =
                ((alpha * b).toInt() + (beta * rgbArray[GREEN]).toInt()).toByte()
            bmpByteArray[index + 2] =
                ((alpha * c).toInt() + (beta * rgbArray[RED]).toInt()).toByte()
            index += 3
        }
    }

    external fun processImage(bmpByteArray: ByteArray, rgbaArray: IntArray)
}