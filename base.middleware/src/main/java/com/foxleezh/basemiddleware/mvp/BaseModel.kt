package com.foxleezh.basemiddleware.mvp

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by foxleezh on 18-5-29.
 */
open class BaseModel : Parcelable {


    protected constructor(parcel: Parcel)

    protected constructor()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {}


}
