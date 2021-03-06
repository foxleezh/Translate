package com.foxleezh.translate

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.foxleezh.basemiddleware.activity.AbstractActivity
import com.foxleezh.basemiddleware.net.API
import com.foxleezh.translate.loading.R
import com.foxleezh.basemiddleware.net.ApiManager
import com.foxleezh.basemiddleware.net.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.*

class MainActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView())
    }

    override fun onResume() {
        super.onResume()
        getData()
        ApiManager.instance.test()
    }


    fun getView() : View {
        return UI {
            verticalLayout {
                gravity = Gravity.CENTER
                textView {
                    text = "123"
                    backgroundColor = Color.RED
                    setOnClickListener { view -> toast("点击了") }
                }
            }
        }.view
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
        RetrofitManager.service.doGetString(API.BASE_URL,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())!!
                .subscribe({ bean ->
                    toast(bean.string())
                }, { throwable ->
                    Toast.makeText(this@MainActivity,throwable.toString(),Toast.LENGTH_SHORT).show()
                })
    }
}
