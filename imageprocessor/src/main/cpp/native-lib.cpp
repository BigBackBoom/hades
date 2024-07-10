#include <jni.h>
#include <malloc.h>

//
// Created by kikuchi.kodai on 2024/06/25.
//

extern "C" JNIEXPORT void JNICALL
Java_com_bbb_imageprocessor_AlphaBlender_processImage(JNIEnv *env,
                                                      jobject thiz,
                                                      jbyteArray bmp_byte_array,
                                                      jintArray rgba_array) {
    jsize size = env->GetArrayLength(bmp_byte_array);

    uint8_t *p = (uint8_t *) malloc(sizeof(uint8_t) * size);
    env->GetByteArrayRegion(bmp_byte_array, 0, size, (jbyte *) p);

    int *rgba = (int *) malloc(sizeof(int) * 4);
    env->GetIntArrayRegion(rgba_array, 0, 4, (jint *) rgba);

    const float a = rgba[3] * 0.01f;
    const float b = 1.0f - (rgba[3] * 0.01f);

    for (int i = 0; i < 1; i++) {
        for (int j = 54; j < size; j = j + 3) {
            p[j] = (a * p[j]) + (b * (uint8_t) rgba[2]);
            p[j + 1] = (a * p[j + 1]) + (b * (uint8_t) rgba[1]);
            p[j + 2] = (a * p[j + 2]) + (b * (uint8_t) rgba[0]);
        }
    }
    env->SetByteArrayRegion(bmp_byte_array, 0, size, (jbyte *) p);
    free(rgba);
    free(p);
}