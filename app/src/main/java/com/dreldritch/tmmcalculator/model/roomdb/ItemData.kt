package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.dreldritch.tmmcalculator.services.ITEMDATA_TABLE

/**
 * Created by cerox on 22.02.2018.
 */
@Entity(tableName = ITEMDATA_TABLE)
data class ItemData(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "description") var description: String,
        @ColumnInfo(name = "price") var price: Double,
        @ColumnInfo(name = "currency") var currency: String,
        @ColumnInfo(name = "date") var date: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeString(currency)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemData> {
        override fun createFromParcel(parcel: Parcel): ItemData {
            return ItemData(parcel)
        }

        override fun newArray(size: Int): Array<ItemData?> {
            return arrayOfNulls(size)
        }
    }
}



