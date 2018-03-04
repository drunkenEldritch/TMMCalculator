package com.dreldritch.tmmcalculator.dialog


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView
import android.widget.TextView

import com.dreldritch.tmmcalculator.R
import kotlinx.android.synthetic.main.deletable_chip_layout.*
import kotlinx.android.synthetic.main.fragment_item_extras.*

class ItemExtrasFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_item_extras, container, false)

        //TODO Add tag database
        val chips = listOf("test1", "test2", "test3", "test4", "test5")
        for(c in chips){
            val chip = inflater!!.inflate(R.layout.deletable_chip_layout, container, false)

            val deletable_chip_txt = chip.findViewById<TextView>(R.id.deletable_chip_txt)
            deletable_chip_txt.text = c

            val linear_chip_container = view.findViewById<LinearLayout>(R.id.linear_chip_container)
            linear_chip_container.addView(chip)
        }
        return view
    }

    companion object {

        fun newInstance(): ItemExtrasFragment {
            val fragment = ItemExtrasFragment()
            return fragment
        }
    }

}// Required empty public constructor
