package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable

const val ITEM_DATA_TABLE = "ItemData"

@Entity(tableName = ITEM_DATA_TABLE,
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = TagData::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("tag_id"),
                        onDelete = ForeignKey.SET_NULL),
                ForeignKey(
                        entity = DateData::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("date_id"),
                        onDelete = ForeignKey.RESTRICT))
)
data class ItemData(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "description") var description: String,
        @ColumnInfo(name = "price") var price: Double,
        @ColumnInfo(name = "currency") var currency: String,
        @ColumnInfo(name = "date_id") var date: String,
        @ColumnInfo(name = "tag_id") var tagId: Long,
        @Ignore val itemTag: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as Long,
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeString(currency)
        parcel.writeString(date)
        parcel.writeValue(tagId)
        parcel.writeString(itemTag)
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



