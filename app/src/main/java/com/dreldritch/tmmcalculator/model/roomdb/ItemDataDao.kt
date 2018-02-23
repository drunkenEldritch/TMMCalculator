package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.dreldritch.tmmcalculator.services.ITEMDATA_TABLE

@Dao
interface ItemDataDao {

    @Insert(onConflict = REPLACE)
    fun insert(itemData: ItemData)

    @Update(onConflict = REPLACE)
    fun update(itemData: ItemData)

    @Delete
    fun delete(itemData: ItemData)

    @Query("SELECT * FROM " + ITEMDATA_TABLE)
    fun getAll(): LiveData<List<ItemData>>

    @Query("DELETE FROM " + ITEMDATA_TABLE)
    fun deleteAll()
}