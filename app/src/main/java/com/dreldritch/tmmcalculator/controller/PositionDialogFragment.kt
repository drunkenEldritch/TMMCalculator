package com.dreldritch.tmmcalculator.controller

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.model.sqlitedb.PositionDBProcessor
import kotlinx.android.synthetic.main.fragment_position_dialog.*
import java.text.SimpleDateFormat
import java.util.*

//TODO Test if fragment is dimissed when clicked on activity while fragment is open

class PositionDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_position_dialog, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbprocessor = PositionDBProcessor(context)

        // Set EditText fields for date and time automatically
        setDateEditText()
        setTimeEditText()

        pos_abort_btn.setOnClickListener { dismiss() }

        pos_ok_button.setOnClickListener {
            dbprocessor.addPosition(
                    pos_name_edit.text.toString(),
                    pos_description_edit.text.toString(),
                    pos_price_edit.text.toString().toDouble(),
                    pos_date_edit.text.toString(),
                    pos_time_edit.text.toString())
            dismiss()
        }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//        private val ARG_PARAM1 = "param1"
//        private val ARG_PARAM2 = "param2"

        fun newInstance(): PositionDialogFragment {
            /*val fragment = PositionDialogFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args*/
            return PositionDialogFragment()
        }
    }

    /**
     * Set current date in EditTextView. For localization use getTimeInstance(),
     * getDateInstance() or getDateTimeInstance().
     */
    private fun setDateEditText(){
        val dateformatter = SimpleDateFormat.getDateInstance()
        val date = dateformatter.format(Date())
        pos_date_edit.setText(date)
    }

    /**
     * Set current time in EditTextView. For localization use getTimeInstance(),
     * getDateInstance() or getDateTimeInstance().
     */
    private fun setTimeEditText(){
        val timeformatter = SimpleDateFormat.getTimeInstance()
        val time = timeformatter.format(Date())
        pos_time_edit.setText(time)
    }
}// Required empty public constructor
