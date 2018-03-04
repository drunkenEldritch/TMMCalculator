package com.dreldritch.tmmcalculator.dialog


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.util.DateHelper
import kotlinx.android.synthetic.main.fragment_item_info.*

class ItemInfoFragment : Fragment() {

    private var datehelper = DateHelper("yyyy-MM-dd")

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_item_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pos_date_edit.setText(datehelper.getCurrentDate())
    }

    companion object {
        fun newInstance(): ItemInfoFragment {
            val fragment = ItemInfoFragment()
            return fragment
        }
    }

}// Required empty public constructor
