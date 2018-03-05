package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

const val DATE_DATA_TABLE = "DateData"

@Entity(tableName = DATE_DATA_TABLE)
data class DateData(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo (name = "name") var date: String)