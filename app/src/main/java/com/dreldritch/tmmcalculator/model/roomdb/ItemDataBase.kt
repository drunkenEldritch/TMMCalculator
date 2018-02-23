package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dreldritch.tmmcalculator.services.DB_NAME


@Database(entities = arrayOf(ItemData::class), version = 1)
abstract class ItemDataBase : RoomDatabase() {

    abstract fun getItemDataDao(): ItemDataDao

    companion object {
        private var INSTANCE: ItemDataBase? = null

        fun getDatabase(context: Context): ItemDataBase {
            if (INSTANCE == null) {
                synchronized(ItemDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ItemDataBase::class.java, DB_NAME)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}