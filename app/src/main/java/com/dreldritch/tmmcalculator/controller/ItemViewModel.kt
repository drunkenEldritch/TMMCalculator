package com.dreldritch.tmmcalculator.controller

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import com.dreldritch.tmmcalculator.model.roomdb.ItemRepository

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val itemRepository = ItemRepository(application)

    fun insert(item: ItemData) = itemRepository.insert(item)
    fun getAllFromMonth(month_regex: String): LiveData<List<ItemData>> = itemRepository.getAllFromMonth(month_regex)
    fun update(item: ItemData) = itemRepository.update(item)
    /*fun getAllFromMonth(): LiveData<List<ItemData>> = itemRepository.getAllFromMonth()*/
    fun getAllItems():LiveData<List<ItemData>> = itemRepository.getAllItems()
}