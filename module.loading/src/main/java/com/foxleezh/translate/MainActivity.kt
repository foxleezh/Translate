package com.foxleezh.translate

import android.os.Bundle
import android.widget.Toast
import com.foxleezh.basemiddleware.activity.AbstractActivity
import com.foxleezh.translate.loading.R
import com.hazz.kotlinmvp.api.ApiManager
import com.hazz.kotlinmvp.net.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        getData()

    }


    /**
     * 获取分类下的 List 数据
     */
    fun getData() {
        val map=HashMap<String,String>()
        map.put("client","gtx")
        map.put("sl","en")
        map.put("tl","zh-CN")
        map.put("dt","t")
        map.put("q","Write your apps for an in-car experience without having to worry about vehicle-specific hardware differences like screen resolution, software interfaces, knobs, and touch controls.")
        RetrofitManager.service.doGetString(ApiManager.BASE_URL,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())!!
                .subscribe({ bean ->
                    Toast.makeText(this@MainActivity,bean.string(),Toast.LENGTH_SHORT).show()
                }, { throwable ->
                    Toast.makeText(this@MainActivity,throwable.toString(),Toast.LENGTH_SHORT).show()
                })
    }
}
