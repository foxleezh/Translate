package com.foxleezh.basemiddleware.net

import android.content.Context


/**
 * Created by foxleezh on 18-5-29.
 * Api 接口
 */

class ApiManager private constructor() {

    private var context:Context?=null

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.NONE) {
            ApiManager()
        }
    }

    fun init(context: Context){
        this.context=context
    }

    fun test() {
        println("test is "+context)
    }




}