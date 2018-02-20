package com.dreldritch.tmmcalculator.model.sqlitedb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class PositionDBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    private val LOG = "PositionDBHandler"

    companion object {
        const val DB_NAME = "position_db"
        const val DB_VERSION = 1
        const val POSITION_TABLE = "Position"
        const val ID = "_id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val PRICE = "price"
        const val DATE = "date"
        const val TIME = "time"

        private const val CREATE_TABLE_POSITION = "CREATE TABLE " + POSITION_TABLE + "(" +
                ID          + " INTEGER PRIMARY KEY, " +
                NAME        + " TEXT, "     +
                DESCRIPTION + " TEXT, "     +
                PRICE       + " REAL, "     +
                DATE        + " TEXT," +
                TIME        + " TEXT" +
                ");"

        private var hInstance: PositionDBHandler? = null

        fun getInstance(context: Context): PositionDBHandler {
            if (hInstance == null) {
                //application context to avoid activity context leak
                hInstance = PositionDBHandler(context.applicationContext)
            }
            return hInstance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(LOG, "Creating: $CREATE_TABLE_POSITION")
        db?.execSQL(CREATE_TABLE_POSITION)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Used if DB table is altered(version incremented)
    }/**/
}
