package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

const val DATE_DATA_TABLE = "DateData"

@Entity(tableName = DATE_DATA_TABLE, indices= arrayOf(Index(value="date", unique=true)))
data class DateData(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo (name = "date") var date: String)