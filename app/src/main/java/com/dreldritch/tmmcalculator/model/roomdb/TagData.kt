package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.*

const val TAG_DATA_TABLE = "TagData"

@Entity(tableName = TAG_DATA_TABLE, indices= arrayOf(Index(value="tag", unique=true)))

class TagData(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "tag") var tag: String
)
