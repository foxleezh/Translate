package com.hazz.kotlinmvp.net

import com.foxleezh.basemiddleware.Luncher
import com.hazz.kotlinmvp.api.ApiManager
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by foxleezh on 18-5-29.
 *
 */

object RetrofitManager{

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    val service: ApiManager by lazy { getRetrofit()!!.create(ApiManager::class.java)}

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    val cacheFile = File(Luncher.context.cacheDir, "cache")
                    val cache = Cache(cacheFile, 1024 * 1024 * 50)

                    client = OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .cache(cache)
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .build()

                    retrofit = Retrofit.Builder()
                            .baseUrl(ApiManager.URL)
                            .client(client!!)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                }
            }
        }
        return retrofit
    }


}
