package com.foxleezh.basecomponent.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import java.io.IOException
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.SocketException
import java.net.URL


/**
 * Created by foxleezh on 18-5-29.
 */
class NetworkUtil{

    companion object {
        /**
         * check NetworkAvailable
         *
         * @param context
         * @return
         */
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val permission = Manifest.permission.ACCESS_NETWORK_STATE
            //查看是否已有权限
            val checkSelfPermission = ActivityCompat.checkSelfPermission(context,permission)
            if (checkSelfPermission  == PackageManager.PERMISSION_GRANTED) {
                val info = manager.activeNetworkInfo
                return !(null == info || !info.isAvailable)
            }
            return false
        }

    }



}