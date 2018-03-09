package com.dreldritch.tmmcalculator.dialog


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.controller.ItemViewModel
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import com.dreldritch.tmmcalculator.util.DateHelper
import kotlinx.android.synthetic.main.fragment_add_item_dialog.*
import kotlinx.android.synthetic.main.fragment_item_info.*


class AddItemFragmentDialog : DialogFragment() {

    private var item: ItemData? = null
    private var datehelper = DateHelper("yyyy-MM-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_add_item_dialog, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addItemAdapter = AddItemDialogPageAdapter(childFragmentManager, listOf(ItemInfoFragment.newInstance(), ItemExtrasFragment.newInstance()))
        item_dialog_pager.adapter = addItemAdapter
        item_dialog_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(add_item_tabs))
        add_item_tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(item_dialog_pager))

        val itemViewModel = ViewModelProviders.of(this.activity).get(ItemViewModel::class.java)

        //Edit-event. Fragment is used to edit an existing item object
        /*if(savedInstanceState != null)
            item = savedInstanceState?.getParcelable(ITEM) as ItemData

        if(item != null){
            setItemFields(item!!)
            pos_date_edit.setText(item!!.date)
        }else*/

        //Button ClickListener
        item_frag_abort_btn.setOnClickListener { dismiss() }

        item_frag_add_btn.setOnClickListener {

            //Validate name
            var name: String? = pos_name_edit.text.toString()
            if(name == "") {
                name = null
                Toast.makeText(this.context, "ItemName is needed!", Toast.LENGTH_SHORT).show()
            }

            //Get description
            val description = pos_description_edit.text.toString()

            //TODO Check two numbers after point
            //Validate price
            val price_str = pos_price_edit.text.toString()
            val price = if(price_str != "")price_str.toDouble() else 0.0

            //Get currency
            val currency = pos_currency_spinner.selectedItem.toString()

            //Validate date
            val date: String? = datehelper.parseDateField(pos_date_edit.text.toString())
            if(date == null)
                Toast.makeText(this.context, "Wrong date format!", Toast.LENGTH_SHORT).show()

            /*if(name != null && date != null){
                if(item == null){
                    item = ItemData(null, name, description, price, currency, date)
                    itemViewModel.insert(item!!)
                    dismiss()
                }else{
                    item!!.name = name
                    item!!.description = description
                    item!!.price = price
                    item!!.currency = currency
                    item!!.date = date
                    itemViewModel.update(item!!)
                    dismiss()
                }
            }*/
        }
    }

    /**
     * New instance of DialogFragment
     */
    companion object {
        // the fragment initialization parameters
        const val ITEM = "item"

        private fun newInstance(item: ItemData?): AddItemFragmentDialog {
            val fragment = AddItemFragmentDialog()

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

    inner class AddItemDialogPageAdapter(fm: FragmentManager, private val pages: List<Fragment>): FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.count()
        }
    }

}// Required empty public constructor
