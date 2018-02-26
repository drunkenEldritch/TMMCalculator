package com.dreldritch.tmmcalculator.controller

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import com.dreldritch.tmmcalculator.model.roomdb.ItemRepository

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val itemRepository = ItemRepository(application)

    fun insert(item: ItemData) = itemRepository.insert(item)
    fun getAllFromMonth(month: Int, year: Int): LiveData<List<ItemData>> = itemRepository.getAllFromMonth(month, year)
    fun getAllItems():LiveData<List<ItemData>> = itemRepository.getAllItems()
}