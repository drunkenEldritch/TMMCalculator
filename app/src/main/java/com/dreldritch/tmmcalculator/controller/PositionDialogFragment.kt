package com.dreldritch.tmmcalculator.controller

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import com.dreldritch.tmmcalculator.services.DateHelper
import kotlinx.android.synthetic.main.fragment_position_dialog.*

class PositionDialogFragment : DialogFragment() {

    private var item: ItemData? = null

    private var datehelper = DateHelper("yyyy-MM-dd")

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_position_dialog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemViewModel = ViewModelProviders.of(this.activity).get(ItemViewModel::class.java)

        //Edit-event. Fragment is used to edit an existing item object
        /*if(savedInstanceState != null)
            item = savedInstanceState?.getParcelable(ITEM) as ItemData

        if(item != null){
            setItemFields(item!!)
            pos_date_edit.setText(item!!.date)
        }else*/

        pos_date_edit.setText(datehelper.getCurrentDate())

        //Button ClickListener
        pos_abort_btn.setOnClickListener { dismiss() }

        pos_ok_button.setOnClickListener {

            //TODO Check input data
            val date: String? = datehelper.parseDateField(pos_date_edit.text.toString())

            if(date == null)
                Toast.makeText(this.context, "Wrong date format!", Toast.LENGTH_SHORT)

            if(item == null){
                item = ItemData(null,
                        pos_name_edit.text.toString(),
                        pos_description_edit.text.toString(),
                        pos_price_edit.text.toString().toDouble(),
                        pos_currency_spinner.selectedItem.toString(),
                        date!!)
            }

            //Add item to DB
            itemViewModel.insert(item!!)
            dismiss()
        }
    }

    /**
     * New instance of DialogFragment
     */
    companion object {
        // the fragment initialization parameters
        const val ITEM = "item"

        private fun newInstance(item: ItemData?): PositionDialogFragment {
            val fragment = PositionDialogFragment()

            if(item == null){
                val args = Bundle()
                args.putParcelable(ITEM, item)
                fragment.arguments = args
            }
            return fragment
        }

        fun newInstance() = newInstance(null)
    }

    /**
     * Load data of passed position into fragment views.
     */
    private fun setItemFields(item: ItemData){
        pos_name_edit.setText(item.name)
        pos_description_edit.setText(item.description)
        pos_price_edit.setText(item.price.toString())
        pos_date_edit.setText(item.date)
    }
}// Required empty public constructor
