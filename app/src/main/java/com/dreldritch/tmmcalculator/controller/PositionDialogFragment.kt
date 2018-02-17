package com.dreldritch.tmmcalculator.controller


import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R


/**
 * A simple [Fragment] subclass.
 * Use the [PositionDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PositionDialogFragment : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_position_dialog, container, false)
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
