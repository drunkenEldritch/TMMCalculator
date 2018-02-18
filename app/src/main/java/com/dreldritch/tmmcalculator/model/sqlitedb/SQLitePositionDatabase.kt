package com.dreldritch.tmmcalculator.model.sqlitedb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.content.ContentValues
import android.database.Cursor
import com.dreldritch.tmmcalculator.model.Position

private const val LOG = "SQLitePositionDatabase"
private const val DB_VERSION = 1

private const val POSITION_TABLE = "Position"
private const val ID = "_id"
private const val NAME = "name"
private const val DESCRIPTION = "description"
private const val PRICE = "price"
private const val DATE = ""

private const val CREATE_TABLE_POSITION = "CREATE TABLE " + POSITION_TABLE + "(" +
        ID          + " INTEGER PRIMARY KEY, " +
        NAME        + " TEXT, "     +
        DESCRIPTION + " TEXT, "     +
        PRICE       + " REAL, "  +
        DATE        + " TEXT" +
        ");"

//TODO db name & version not hardcoded
class SQLitePositionDatabase(context: Context) : SQLiteOpenHelper(context, "position_db", null, 1) {

    lateinit var position: Position

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(LOG, "Creating: $CREATE_TABLE_POSITION")
        db?.execSQL(CREATE_TABLE_POSITION) ?: Log.e(LOG, "DB is null")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Used if DB table is altered(version incremented)
    }

    //Creating, updating, deleting
    fun saveRecord(id: Long?, name: String, description: String, price: Double, date: String) {
        if (id!=null) {
            updateRecord(id, name, description, price, date)
        } else {
            addPosition(name, description, price, date)
        }
    }

    private fun addPosition(name: String, description: String, price: Double, date: String): Long{
        val db = writableDatabase
        val values = ContentValues()
        values.put(NAME, name)
        values.put(DESCRIPTION, description)
        values.put(PRICE, price)
        values.put(DATE, date)
        return db.insert(POSITION_TABLE, null, values)
    }

    fun updateRecord(id: Long, name: String, description: String, price: Double, date: String): Int {
        val db = writableDatabase
        val values = ContentValues()
        values.put(ID, id)
        values.put(NAME, name)
        values.put(DESCRIPTION, description)
        values.put(PRICE, price)
        values.put(DATE, date)
        return db.update(POSITION_TABLE, values, "_id = ?", arrayOf(id.toString()))
    }

    fun deleteRecord(id: Long): Int {
        val db = writableDatabase
        return db.delete(POSITION_TABLE, "_id = ?", arrayOf(id.toString()))
    }

    //Reading values
    //TODO Throw exception if query fails
    private fun getPosition(_id: Long): Position? {

        val db: SQLiteDatabase = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM " + POSITION_TABLE +
                " WHERE " + ID + " = ?", arrayOf(_id.toString()))

        Log.i(LOG, "Cursor.getCount() = ${cursor.count}")

        if (cursor.count > 0) {
            Log.d(LOG, "id = ${cursor.getLong(0)}\n" +
                    "name = ${cursor.getString(1)}\n" +
                    "desc = ${cursor.getString(2)}\n" +
                    "price = ${cursor.getDouble(3)}\n" +
                    "date = ${cursor.getString(4)}\n"
            )

            position = Position(cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getDouble(3),
                cursor.getString(4))
            cursor.close()
        }

        return  if(position!=null) position else null
    }


}