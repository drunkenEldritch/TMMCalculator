package com.dreldritch.tmmcalculator.dialog


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R

class ItemExtrasFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_item_extras, container, false)
    }

    companion object {

        fun newInstance(): ItemExtrasFragment {
            val fragment = ItemExtrasFragment()
            return fragment
        }
    }

}// Required empty public constructor
