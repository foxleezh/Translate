package com.foxleezh.translate.fragments

import android.content.Intent
import android.os.Handler
import android.os.Message
import com.foxleezh.basemiddleware.mvp.BaseFragment
import com.foxleezh.translate.MainActivity
import com.foxleezh.translate.iviews.ILoadingView
import com.foxleezh.translate.loading.R
import com.foxleezh.translate.presenters.LoadingPresenter
import java.lang.ref.WeakReference

/**
 * Created by foxleezh on 18-5-29.
 */
class LoadingFragment : BaseFragment<ILoadingView,LoadingPresenter>() , ILoadingView {

    // 静态常量
    companion object {
        const val MESSAGE_JUMP = 1001
    }

    private var handler: MyHandler= MyHandler(this@LoadingFragment)

    override fun getPresenter(): LoadingPresenter {
        return LoadingPresenter(activity?.applicationContext)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_loading

    override val mvpView: ILoadingView
        get() = this@LoadingFragment

    override fun init() {
        handler.sendEmptyMessageDelayed(MESSAGE_JUMP,2000)
    }

    class MyHandler(activity: LoadingFragment) : Handler() {

        private val mfragment: WeakReference<LoadingFragment> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            if (mfragment.get() == null) {
                return
            }
            val fragment = mfragment.get()

            if(fragment?.activity!!.isFinishing){
                return
            }

            when (msg.what) {
                MESSAGE_JUMP -> {
                    fragment.startActivity(Intent(fragment.activity, MainActivity::class.java))
                    fragment.getActivity()?.finish()
                }
                else -> {
                }
            }
        }
    }

}