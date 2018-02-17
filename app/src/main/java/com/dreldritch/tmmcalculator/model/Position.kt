package com.dreldritch.tmmcalculator.model

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cerox on 17.02.2018.
 */
class Position(var name: String, var description: String, var price: Double, var date: Date, var tags: ArrayList<String>){
    fun addTag(tag: String) = tags.add(tag)
}