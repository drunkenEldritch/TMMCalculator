package com.dreldritch.tmmcalculator.controller


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.model.Position
import com.dreldritch.tmmcalculator.model.sqlitedb.PositionDBProcessor
import kotlinx.android.synthetic.main.fragment_position_dialog.*
import java.text.SimpleDateFormat
import java.util.*

//TODO Test if fragment is dimissed when clicked on activity while fragment is open

class PositionDialogFragment : DialogFragment() {

    private var position: Position? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_position_dialog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbprocessor = PositionDBProcessor(context)
        if(savedInstanceState != null)
            position = savedInstanceState.getParcelable(POSITION) as Position

        if(position != null){
            setPositionFields(position!!)
            setDateField(Date())
        }else
            setDateField(Date())

        pos_abort_btn.setOnClickListener { dismiss() }

        pos_ok_button.setOnClickListener {
            if(position == null){
                position = Position(null,
                        pos_name_edit.text.toString(),
                        pos_description_edit.text.toString(),
                        pos_price_edit.text.toString().toDouble(),
                        pos_date_edit.text.toString())
            }
            dbprocessor.savePosition(null, position!!)
            dismiss()
        }
    }

    companion object {
        // the fragment initialization parameters
        private val POSITION = "position"

        fun newInstance(position: Position?): PositionDialogFragment {
            val fragment = PositionDialogFragment()

            if(position == null){
                val args = Bundle()
                args.putParcelable(POSITION, position)
                fragment.arguments = args
            }
            return fragment
        }

        fun newInstance() = newInstance(null)
    }

    /**
     * Set current date and time in EditTextView. For localization use getTimeInstance(),
     * getDateInstance() or getDateTimeInstance().
     */
    private fun setDateField(current: Date){

        val dateformatter = SimpleDateFormat.getDateInstance()
        val date = dateformatter.format(current)
        pos_date_edit.setText(date)
    }

    /**
     * Load data of passed position into fragment views.
     */
    private fun setPositionFields(position: Position){
        pos_name_edit.setText(position._name)
        pos_description_edit.setText(position._description)
        pos_price_edit.setText(position._price.toString())
        pos_date_edit.setText(position._date)
    }
}// Required empty public constructor
