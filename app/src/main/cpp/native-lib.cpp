//
// Created by ergun on 16/03/2020.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_lastfmtopcharts_di_ApiModule_stringFromJNI(JNIEnv *env, jobject object) {
    std::string hello = "YOUR Key";
    return env->NewStringUTF(hello.c_str());
}