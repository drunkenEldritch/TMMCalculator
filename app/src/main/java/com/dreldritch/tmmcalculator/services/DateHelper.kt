package com.dreldritch.tmmcalculator.services

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by cerox on 25.02.2018.
 */
class DateHelper() {

    var format = "dd/MM/yyyy"
    var day = 0
    var month = 0
    var year = 0

    /**
     * Set current date and time in EditTextView. For localization use getTimeInstance(),
     * getDateInstance() or getDateTimeInstance().
     */
    fun getCurrentDate(): String {
        //val dateformater = SimpleDateFormat.getDateInstance()
        val dateformater = SimpleDateFormat(format)
        return dateformater.format(Date())
    }

    //TODO test date
    fun parseDateField(datestring: String) {
        val dateformat = SimpleDateFormat(format)
        lateinit var date: Date
        try {
            date = dateformat.parse(datestring)
        }catch(e: ParseException){
            e.printStackTrace()
        }
        val cal = Calendar.getInstance()
        cal.time = date
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH) + 1
        year = cal.get(Calendar.YEAR)
    }


}