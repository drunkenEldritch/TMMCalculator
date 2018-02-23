package com.dreldritch.tmmcalculator.model.sqlitedb

import android.os.Parcel
import android.os.Parcelable

class Position(val _id: Long?, var _name: String, var _description: String,
               var _price: Double, var _date: String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(_id)
        parcel.writeString(_name)
        parcel.writeString(_description)
        parcel.writeDouble(_price)
        parcel.writeString(_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Position> {
        override fun createFromParcel(parcel: Parcel): Position {
            return Position(parcel)
        }

        override fun newArray(size: Int): Array<Position?> {
            return arrayOfNulls(size)
        }
    }
}