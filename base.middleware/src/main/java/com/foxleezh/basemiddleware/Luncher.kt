package com.foxleezh.basemiddleware

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.foxleezh.basemiddleware.net.ApiManager
import kotlin.properties.Delegates

/**
 * Created by foxleezh on 18-5-29.
 */

class Luncher {

    companion object {
        lateinit var context: Context
    }

    fun OnCreate(application: Application) {
        var isSelfProcess = true
        val pid = android.os.Process.myPid()
        val processAppName = getAppName(pid, application)
        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止初始化2次，加此判断会保证S被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
        // name就立即返回
        if (processAppName == null || !processAppName.equals(application.packageName, ignoreCase = true)) {
            isSelfProcess = false
        }
        if (isSelfProcess) {
            context=application
            ApiManager.instance.init(context)
        }
    }


    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private fun getAppName(pid: Int, application: Application): String? {
        var processName: String? = null
        val activityManager = application.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = activityManager.runningAppProcesses
        val i = list.iterator()
        while (i.hasNext()) {
            val info = i.next() as ActivityManager.RunningAppProcessInfo
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName
                    // 返回当前进程名
                    return processName
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        // 没有匹配的项，返回为null
        return null
    }

}
