package com.dreldritch.tmmcalculator.model.roomdb

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context

const val DB_NAME = "item_db"

@Database(entities = arrayOf(ItemData::class, TagData::class, DateData::class), version = 1)
abstract class ItemDataBase : RoomDatabase() {

    abstract fun getItemDataDao(): ItemDataDao
    abstract fun getTagDataDao(): TagDataDao

    companion object {
        private var INSTANCE: ItemDataBase? = null

        fun getDatabase(context: Context): ItemDataBase {
            if (INSTANCE == null) {
                synchronized(ItemDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ItemDataBase::class.java, DB_NAME)
                            .addMigrations(Migration_1_2())
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }

    class Migration_1_2: Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ItemData " +
                    "ADD COLUMN tag_id LONG DEFAULT NULL")

            database.execSQL("ALTER TABLE ItemData " +
                    "ADD COLUMN date_id LONG")
        }
    }
}