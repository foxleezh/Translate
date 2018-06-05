object Versions {

    // App编译版本
    val APP_COMPILE_SDK_VERSION=27
    val APP_BUILD_TOOLS_VERSION="27.0.3"
    val APP_MIN_SDK_VERSION=16
    val APP_TARGET_SDK_VERSION=27
    val APP_ANDROID_SUPPORT_VERSION="27.1.1"

    //三方库版本
    val KOTLIN_VERSION = "1.1.51"
    val ANKO_VERSION = "0.10.5"
    val RETROFIT_VERSION = "2.4.0"
    val RXJAVA_VERSION = "2.1.9"
    val BUTTERKNIFE_VERSION = "8.4.0"
}

object Libs {
    val butterknife = "com.jakewharton:butterknife:${Versions.BUTTERKNIFE_VERSION}"
    val butterknife_processor = "com.jakewharton:butterknife-compiler:${Versions.BUTTERKNIFE_VERSION}"
}
