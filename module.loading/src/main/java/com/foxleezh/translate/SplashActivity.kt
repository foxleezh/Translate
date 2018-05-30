package com.foxleezh.translate

import android.os.Bundle
import com.foxleezh.basemiddleware.activity.AbstractActivity
import com.foxleezh.translate.fragments.SplashFragment
import com.foxleezh.translate.loading.R

/**
 * 闪屏页
 */


class SplashActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = SplashFragment()
        transaction.replace(R.id.fl_parent, fragment)
        transaction.commit()
    }
}
