package com.dreldritch.tmmcalculator.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.services.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPositionActionButton.setOnClickListener {
            showPositionDialog()
        }
    }

    private fun showPositionDialog(){
        val fm: FragmentManager = supportFragmentManager
        val posDialog:PositionDialogFragment = PositionDialogFragment.newInstance()
        posDialog.show(fm, POSITION_DIALOG_FRAGMENT)
    }
}
