package com.foxleezh.basemiddleware.mvp

/**
 * Created by foxleezh on 18-5-29.
 */
interface MvpPresenter<in V : MvpView> {


  fun attachView(view: V)

  fun detachView()

}
