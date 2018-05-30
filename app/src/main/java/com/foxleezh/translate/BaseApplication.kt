package com.foxleezh.translate

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.foxleezh.basemiddleware.Luncher


/**
 * Created by foxleezh on 18-5-29.
 */

class BaseApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        Luncher().OnCreate(this)
    }
}
