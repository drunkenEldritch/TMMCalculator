package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

const val TAG_DATA_TABLE = "TagData"

@Entity(tableName = TAG_DATA_TABLE)

class TagData(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "tag_name") var tagName: String
)
