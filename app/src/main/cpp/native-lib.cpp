#include <jni.h>
#include <string>

//extern "C" JNIEXPORT jstring JNICALL
//
//Java_com_bbb_hades_features_ImageProcessingScreen_stringFromJNI(
//        JNIEnv* env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
//extern "C" JNIEXPORT jstring JNICALL
//Java_com_bbb_hades_features_ImageProcessingScreenKt_stringFromJNI(
//        JNIEnv *env,
//        jclass clazz) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}


extern "C"
JNIEXPORT void JNICALL
Java_com_bbb_hades_viewmodel_ImageProcessingViewModel_processImage(
        JNIEnv *env,
        jobject thiz,
        jbyteArray bmp_byte_array) {

    jsize size = env->GetArrayLength(bmp_byte_array);

//    jbyte *result_bytes = env->GetByteArrayElements(bmp_byte_array, NULL);
//
//    for (int i = 0; i < 50; i++) {
//        for (int j = 54; j < size; j = j + 3) {
//            result_bytes[j] = 0;
//            result_bytes[j + 1] = 0;
//            result_bytes[j + 2] = -1;
//        }
//    }

    uint8_t* p = (uint8_t * )malloc(sizeof(uint8_t) * size);
    env->GetByteArrayRegion(bmp_byte_array, 0, size, (jbyte *)p);

    const float a = 0.7;
    const float b = 0.3;

    for (int i = 0; i < 1; i++) {
        for (int j = 54; j < size; j = j + 3) {
            p[j] = (a * p[j]) + (b * (uint8_t)0);
            p[j+1] = (a * p[j+1]) + (b * (uint8_t)0);
            p[j+2] = (a * p[j+2]) + (b * (uint8_t)255);
        }
    }
    env -> SetByteArrayRegion(bmp_byte_array, 0, size , (jbyte *)p);
}