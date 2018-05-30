package com.foxleezh.basemiddleware

import android.app.Application
import android.content.Context

/**
 * Created by foxleezh on 18-5-29.
 */

class ModuleApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        Luncher().OnCreate(this)
    }
}
