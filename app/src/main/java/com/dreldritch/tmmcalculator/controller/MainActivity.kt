package com.dreldritch.tmmcalculator.controller

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.services.*
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.ViewModelProviders
import com.dreldritch.tmmcalculator.model.roomdb.ItemData


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val expList = expListView
        val itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java!!)

        itemViewModel.getAllFromMonth("%2018-02%").observe(this,
                Observer<List<ItemData>> { info -> expList.setAdapter(ExpandableDateAdapter(this, info!!)) })

        addPositionActionButton.setOnClickListener {
            showPositionDialog()
        }
    }

    private fun showPositionDialog(){
        val fm: FragmentManager = supportFragmentManager
        val posDialog: ItemDialogFragment = ItemDialogFragment.newInstance()
        posDialog.show(fm, POSITION_DIALOG_FRAGMENT)
    }
}
