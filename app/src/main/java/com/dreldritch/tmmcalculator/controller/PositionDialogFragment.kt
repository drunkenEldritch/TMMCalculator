package com.dreldritch.tmmcalculator.controller

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import com.dreldritch.tmmcalculator.model.roomdb.ItemRepository
import kotlinx.android.synthetic.main.fragment_position_dialog.*
import java.text.SimpleDateFormat
import java.util.*

//TODO Test if fragment is dimissed when clicked on activity while fragment is open
//TODO Check date format before adding into DB
class PositionDialogFragment : DialogFragment() {

    private var item: ItemData? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_position_dialog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemViewModel = ViewModelProviders.of(this.activity).get(ItemViewModel::class.java)
        /*val item_repo = ItemRepository(activity.application)*/

        //Edit-event. Fragment is used to edit an existing item object
        if(savedInstanceState != null)
            item = savedInstanceState.getParcelable(ITEM) as ItemData

        if(item != null){
            setItemFields(item!!)
            setDateField(Date())
        }else
            setDateField(Date())

        pos_abort_btn.setOnClickListener { dismiss() }

        pos_ok_button.setOnClickListener {
            if(item == null){
                item = ItemData(null,
                        pos_name_edit.text.toString(),
                        pos_description_edit.text.toString(),
                        pos_price_edit.text.toString().toDouble(),
                        pos_currency_spinner.getSelectedItem().toString(),
                        pos_date_edit.text.toString())
            }

            //Add item to DB
            //TODO add DB insert(async)
            itemViewModel.insert(item!!)
            dismiss()
        }
    }

    companion object {
        // the fragment initialization parameters
        private val ITEM = "item"

        fun newInstance(item: ItemData?): PositionDialogFragment {
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
    private fun setItemFields(item: ItemData){
        pos_name_edit.setText(item.name)
        pos_description_edit.setText(item.description)
        pos_price_edit.setText(item.price.toString())
        pos_date_edit.setText(item.date)
    }
}// Required empty public constructor
