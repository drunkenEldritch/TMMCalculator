package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.dreldritch.tmmcalculator.util.ITEMDATA_TABLE

@Dao
interface ItemDataDao {

    @Insert(onConflict = REPLACE)
    fun insert(itemData: ItemData)

    @Update(onConflict = REPLACE)
    fun update(itemData: ItemData)

    @Delete
    fun delete(itemData: ItemData)

    @Query("DELETE FROM $ITEMDATA_TABLE")
    fun deleteAll()

    @Query("SELECT * FROM $ITEMDATA_TABLE" )
    fun getAll(): LiveData<List<ItemData>>

    @Query("SELECT * FROM ${ITEMDATA_TABLE} WHERE date LIKE :month")
    fun getAllFromMonth(month: String): LiveData<List<ItemData>>

    /*@Query("SELECT * FROM ${ITEMDATA_TABLE} WHERE date LIKE '%2018-02%'")
    fun getAllFromMonth(): LiveData<List<ItemData>>*/

    /*@Query("SELECT * FROM ${ITEMDATA_TABLE} WHERE month = :month AND year = :year")
    fun getDays(month: Int, year: Int): LiveData<List<ItemData>>*/
}