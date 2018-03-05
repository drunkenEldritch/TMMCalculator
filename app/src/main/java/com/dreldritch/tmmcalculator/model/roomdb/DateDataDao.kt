package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface DateDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dateData: DateData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(dateData: DateData)

    @Delete
    fun delete(dateData: DateData)

    @Query("DELETE FROM $DATE_DATA_TABLE")
    fun deleteAll()

    @Query("SELECT * FROM $DATE_DATA_TABLE" )
    fun getAll(): LiveData<List<DateData>>
}