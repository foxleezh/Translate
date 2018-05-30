package com.foxleezh.translate.presenters

import android.content.Context
import com.foxleezh.basemiddleware.mvp.MvpBasePresenter
import com.foxleezh.translate.iviews.ILoadingView

/**
 * Created by foxleezh on 18-5-29.
 */
class LoadingPresenter(context :Context?) : MvpBasePresenter<ILoadingView>(){

    init {
        this.mContext=context;
    }
}