package com.dreldritch.tmmcalculator.controller

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import com.dreldritch.tmmcalculator.model.roomdb.ItemRepository

/**
 * Created by cerox on 23.02.2018.
 */
class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val itemRepository = ItemRepository(application)


    fun insert(item: ItemData) = itemRepository.insert(item)

}