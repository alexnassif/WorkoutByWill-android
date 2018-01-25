package com.alexnassif.mobile.workoutbywill.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by raymond on 1/22/18.
 */
class Exercise(val name: String, val how: String, val why: String, val imageAfter: String, val imageBefore: String) :Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {

        p0?.writeString(name)
        p0?.writeString(how)
        p0?.writeString(why)
        p0?.writeString(imageAfter)
        p0?.writeString(imageBefore)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object  {

        @JvmField val CREATOR = object : Parcelable.Creator<Exercise>
        {
            override fun createFromParcel(parcel: Parcel): Exercise {
                return Exercise(parcel)
            }

            override fun newArray(size: Int): Array<Exercise?> {
                return arrayOfNulls(size)
            }
        }
    }
}