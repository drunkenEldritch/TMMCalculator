package com.dreldritch.tmmcalculator.services

import android.util.Log
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by cerox on 25.02.2018.
 */
class DateHelper(private val pref_format: String) {

    private val tag = "DateHelper"

    companion object {
        val DATESORT = "yyyy-MM-dd"
        val DATESLASH = "dd/MM/yyyy"
        val DATEPOINT = "dd.MM.yyyy"
    }

    /**
     * Set current date and time in EditTextView. For localization use getTimeInstance(),
     * getDateInstance() or getDateTimeInstance().
     */
    fun getCurrentDate(): String {
        //val dateformater = SimpleDateFormat.getDateInstance()
        val dateformater = SimpleDateFormat(pref_format)
        return dateformater.format(Date())
    }

    //TODO test date
    fun parseDateField(datestring: String): String? {

        var format = if(datestring.matches(Regex("([0-9]{2})/([0-9]{2})/([0-9]{4})"))) DATESLASH
        else if(datestring.matches(Regex("([0-9]{2}).([0-9]{2}).([0-9]{4})"))) DATEPOINT
        else if(datestring.matches(Regex("([0-9]{4})-([0-9]{2})-([0-9]{2})"))) DATESORT
        else return null

        var date: Date? = null
        val inputFormat = SimpleDateFormat(format)
        val outputFormat = SimpleDateFormat(DATESORT)

        try {
            date = inputFormat.parse(datestring)
        }catch(e: ParseException){
            e.printStackTrace()
            Log.e(tag, "Wrong format: $datestring.")
        }
        return outputFormat.format(date)
    }


}