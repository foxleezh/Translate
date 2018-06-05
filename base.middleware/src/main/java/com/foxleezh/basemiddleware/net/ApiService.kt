package com.foxleezh.basemiddleware.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * Created by foxleezh on 18-5-29.
 * Api 接口
 */

interface ApiService {

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