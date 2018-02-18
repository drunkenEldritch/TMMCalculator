package com.dreldritch.tmmcalculator.controller


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R
import kotlinx.android.synthetic.main.fragment_position_dialog.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [PositionDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PositionDialogFragment : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_position_dialog, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timeformatter = SimpleDateFormat("hh:mm")
        val dateformatter = SimpleDateFormat("yyyy/MM/dd")
        val time = timeformatter.format(Date())
        val date = dateformatter.format(Date())

        pos_date_edit.setText(date)
        pos_time_edit.setText(time)
    }


    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//        private val ARG_PARAM1 = "param1"
//        private val ARG_PARAM2 = "param2"

        fun newInstance(): PositionDialogFragment {
            val fragment = PositionDialogFragment()
            val args = Bundle()
            /*args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args*/

            return fragment
        }
    }

}// Required empty public constructor
