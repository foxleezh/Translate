package com.foxleezh.basemiddleware.mvp

import android.app.Application
import android.content.Context

import java.lang.ref.WeakReference

/**
 * MvpPresenter的基本实现类
 */
open class MvpBasePresenter<V : MvpView> : MvpPresenter<V> {

    constructor()

    constructor(context: Context?){
        if (context is Application) {
            this.mContext = context
        } else {
            throw IllegalArgumentException("context must be ApplicationContext")
        }
    }

    protected var mContext: Context?= null

    private var viewRef: WeakReference<V>? = null


    protected val view: V?
        get() = if (viewRef == null) null else viewRef?.get()


    protected val isViewAttached: Boolean
        get() = viewRef != null && viewRef?.get() != null

    override fun attachView(view: V) {
        viewRef = WeakReference(view)
    }

    override fun detachView() {
        if (viewRef != null) {
            viewRef?.clear()
            viewRef = null
        }
    }
}
