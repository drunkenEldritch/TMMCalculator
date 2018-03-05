package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface ItemDataDao {

    @Insert(onConflict = REPLACE)
    fun insert(itemData: ItemData)

    @Update(onConflict = REPLACE)
    fun update(itemData: ItemData)

    @Delete
    fun delete(itemData: ItemData)

    @Query("DELETE FROM $ITEM_DATA_TABLE")
    fun deleteAll()

    @Query("SELECT * FROM $ITEM_DATA_TABLE" )
    fun getAll(): LiveData<List<ItemData>>

    @Query("SELECT * FROM ${ITEM_DATA_TABLE} WHERE date LIKE :month")
    fun getAllFromMonth(month: String): LiveData<List<ItemData>>

    /*@Query("SELECT * FROM ${ITEM_DATA_TABLE} WHERE date LIKE '%2018-02%'")
    fun getAllFromMonth(): LiveData<List<ItemData>>*/

    /*@Query("SELECT * FROM ${ITEM_DATA_TABLE} WHERE month = :month AND year = :year")
    fun getDays(month: Int, year: Int): LiveData<List<ItemData>>*/
}