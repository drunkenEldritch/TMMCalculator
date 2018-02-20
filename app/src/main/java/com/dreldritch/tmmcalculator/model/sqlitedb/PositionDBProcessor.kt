package com.dreldritch.tmmcalculator.model.sqlitedb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.dreldritch.tmmcalculator.model.Position

class PositionDBProcessor(context: Context){

    private val LOG = "PositionDBProcessor"
    val dbhelper = PositionDBHandler.getInstance(context)

    //Creating, updating, deleting
    fun saveRecord(id: Long?, name: String, description: String, price: Double, date: String, time: String) {
        if (id!=null) {
            updateRecord(id, name, description, price, date, time)
        } else {
            addPosition(name, description, price, date, time)
        }
    }

    fun addPosition(name: String, description: String, price: Double, date: String, time: String): Long{
        val db = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(PositionDBHandler.NAME, name)
        values.put(PositionDBHandler.DESCRIPTION, description)
        values.put(PositionDBHandler.PRICE, price)
        values.put(PositionDBHandler.DATE, date)
        values.put(PositionDBHandler.TIME, time)
        val result = db.insert(PositionDBHandler.POSITION_TABLE, null, values)
        db.close()
        return result
    }

    fun updateRecord(id: Long, name: String, description: String, price: Double, date: String, time: String): Int {
        val db = dbhelper.writableDatabase
        val values = ContentValues()
        values.put(PositionDBHandler.ID, id)
        values.put(PositionDBHandler.NAME, name)
        values.put(PositionDBHandler.DESCRIPTION, description)
        values.put(PositionDBHandler.PRICE, price)
        values.put(PositionDBHandler.DATE, date)
        values.put(PositionDBHandler.TIME, time)
        val result =  db.update(PositionDBHandler.POSITION_TABLE, values, "_id = ?", arrayOf(id.toString()))
        db.close()
        return result
    }

    fun deleteRecord(id: Long): Int {
        val db = dbhelper.writableDatabase
        val result = db.delete(PositionDBHandler.POSITION_TABLE, "_id = ?", arrayOf(id.toString()))
        db.close()
        return result
    }

    //Reading values
    //TODO Throw exception if query fails
    private fun getPosition(_id: Long): Position {

        var position: Position? = null
        val db = dbhelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM " + PositionDBHandler.POSITION_TABLE +
                " WHERE " + PositionDBHandler.ID + " = ?", arrayOf(_id.toString()))

        Log.i(LOG, "Cursor.getCount() = ${cursor.count}")

        //TODO Enum for columns?
        if (cursor.count > 0) {
            Log.d(LOG, "id = ${cursor.getLong(0)}\n" +
                    "name = ${cursor.getString(1)}\n" +
                    "desc = ${cursor.getString(2)}\n" +
                    "price = ${cursor.getDouble(3)}\n" +
                    "date = ${cursor.getString(4)}\n" +
                    "date = ${cursor.getString(5)}\n"
            )

            position = Position(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getString(4),
                    cursor.getString(5))
            cursor.close()
        }
        return position!!
    }
}
