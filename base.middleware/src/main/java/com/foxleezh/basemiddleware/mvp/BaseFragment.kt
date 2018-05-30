package com.foxleezh.basemiddleware.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by foxleezh on 18-5-29.
 */

abstract class BaseFragment<V : MvpView, T : MvpBasePresenter<V>> : Fragment(){
    protected var mPresenter: T? = null
    protected var rootView: View? = null
    private var unbind: Unbinder? = null

    protected abstract val layoutRes: Int

    protected abstract val mvpView: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (rootView == null) {
            val layoutRes = layoutRes
            rootView = activity?.layoutInflater?.inflate(layoutRes, null)
            unbind = ButterKnife.bind(this, rootView!!)
            init()
        } else {
            if (rootView?.parent != null) {
                (rootView?.parent as ViewGroup).removeView(rootView)
            }
            unbind = ButterKnife.bind(this, rootView!!)
        }
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.attachView(mvpView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (unbind != null) {
            unbind?.unbind()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

    }

    protected abstract fun init()

    protected abstract fun getPresenter() : T


    fun finish() {

    }

    fun onBackPressed() {

    }

    fun onRelease() {

    }

}