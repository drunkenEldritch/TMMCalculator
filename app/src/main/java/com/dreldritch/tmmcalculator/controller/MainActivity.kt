package com.dreldritch.tmmcalculator.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.util.*
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.support.design.widget.Snackbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPositionActionButton.setOnClickListener {view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        testBtn.setOnClickListener {
            val intent = Intent(this, TabbedListActivity::class.java)
            startActivity(intent)
        }
    }
}
