package com.foxleezh.translate.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.foxleezh.basemiddleware.mvp.*
import com.foxleezh.translate.LoadingActivity
import com.foxleezh.translate.loading.R

/**
 * Created by foxleezh on 18-5-29.
 */
class SplashFragment : BaseFragment<MvpView,MvpBasePresenter<MvpView>>() , MvpView {

    override fun getPresenter(): MvpBasePresenter<MvpView> {
        return MvpBasePresenter(activity?.applicationContext)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_splash

    override val mvpView: MvpView
        get() = this@SplashFragment

    override fun init() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.startActivity(Intent(activity,LoadingActivity::class.java))
        activity?.finish()
    }
}