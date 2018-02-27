package com.dreldritch.tmmcalculator.model.roomdb

import android.os.AsyncTask
import android.app.Application
import android.arch.lifecycle.LiveData

class ItemRepository(application: Application) {

    private val itemDao: ItemDataDao

    init {
        val db = ItemDataBase.getDatabase(application)
        itemDao = db.getItemDataDao()
    }

    //Room executes all queries on a separate thread
    fun getAllItems():LiveData<List<ItemData>> = itemDao.getAll()
    fun getAllFromMonth(month_regex: String):LiveData<List<ItemData>> = itemDao.getAllFromMonth(month_regex)
    /*fun getAllFromMonth():LiveData<List<ItemData>> = itemDao.getAllFromMonth()*/

    //TODO Check why insert need async task
    fun insert(item: ItemData) {
        InsertAsyncTask(itemDao).execute(item)
    }

    fun update(item: ItemData) {
        UpdateAsyncTask(itemDao).execute(item)
    }

    private class InsertAsyncTask(val asyncTaskDao: ItemDataDao) : AsyncTask<ItemData, Void, Void>() {
        override fun doInBackground(vararg item: ItemData): Void? {
            asyncTaskDao.insert(item[0])
            return null
        }
    }

    private class UpdateAsyncTask(val asyncTaskDao: ItemDataDao) : AsyncTask<ItemData, Void, Void>() {
        override fun doInBackground(vararg item: ItemData): Void? {
            asyncTaskDao.update(item[0])
            return null
        }
    }

}