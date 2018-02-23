package com.dreldritch.tmmcalculator.model.roomdb

import android.os.AsyncTask
import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import com.dreldritch.tmmcalculator.services.DB_NAME

class ItemRepository(application: Application) {

    private val itemDao: ItemDataDao

    init {
        val db = ItemDataBase.getDatabase(application)
        itemDao = db.getItemDataDao()
    }

    //Room executes all queries on a separate thread
    fun getAllItems():LiveData<List<ItemData>> = itemDao.getAll()

    //TODO Check why insert need async task
    fun insert(item: ItemData) {
        InsertAsyncTask(itemDao).execute(item)
    }

    private class InsertAsyncTask(val asyncTaskDao: ItemDataDao) : AsyncTask<ItemData, Void, Void>() {
        override fun doInBackground(vararg item: ItemData): Void? {
            asyncTaskDao.insert(item[0])
            return null
        }
    }
}