package com.dreldritch.tmmcalculator.model.sqlitedb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class PositionDBProcessor(context: Context){

    private val LOG = "PositionDBProcessor"
    val dbhelper = PositionDBHandler.getInstance(context)

    //Creating, updating, deleting
    fun savePosition(id: Long?, position: Position) {
        if (id!=null) {
            updatePosition(position)
        } else {
            addPosition(position)
        }
    }

    private fun addPosition(position: Position): Long{

        val values = ContentValues()
        values.put(PositionDBHandler.NAME, position._name)
        values.put(PositionDBHandler.DESCRIPTION, position._description)
        values.put(PositionDBHandler.PRICE, position._price)
        values.put(PositionDBHandler.DATE, position._date)

        val db = dbhelper.writableDatabase
        val result = db.insert(PositionDBHandler.POSITION_TABLE, null, values)
        db.close()
        return result
    }

    private fun updatePosition(position: Position): Int {

        val values = ContentValues()
        values.put(PositionDBHandler.ID, position._id)
        values.put(PositionDBHandler.NAME, position._name)
        values.put(PositionDBHandler.DESCRIPTION, position._description)
        values.put(PositionDBHandler.PRICE, position._price)
        values.put(PositionDBHandler.DATE, position._date)

        val db = dbhelper.writableDatabase
        val result =  db.update(PositionDBHandler.POSITION_TABLE, values, "_id = ?", arrayOf(position._id.toString()))
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
        return position!!
    }
}
