package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface TagDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tagData: TagData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(tagData: TagData)

    @Delete
    fun delete(tagData: TagData)

    @Query("DELETE FROM $TAG_DATA_TABLE")
    fun deleteAll()

    @Query("SELECT * FROM $TAG_DATA_TABLE" )
    fun getAll(): LiveData<List<TagData>>
}