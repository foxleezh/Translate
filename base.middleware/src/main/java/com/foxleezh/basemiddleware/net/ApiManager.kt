package com.hazz.kotlinmvp.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


/**
 * Created by foxleezh on 18-5-29.
 * Api 接口
 */

interface ApiManager {

    companion object {
        val URL = "http://translate.google.cn/translate_a/single/"
        val BASE_URL = "http://translate.google.cn/translate_a/single"
    }

    /**
     * get请求
     */
    @GET()
    fun doGetString(@Url newsId: String, @QueryMap maps: Map<String, String>): Observable<ResponseBody>

    /**
     * post请求
     */
    @POST()
    fun doPostString(@Url newsId: String, @FieldMap maps: Map<String, String>): Observable<ResponseBody>



}