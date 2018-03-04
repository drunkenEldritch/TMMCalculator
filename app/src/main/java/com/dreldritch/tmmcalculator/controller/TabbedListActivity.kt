package com.dreldritch.tmmcalculator.controller

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.dialog.AddItemFragmentDialog
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import kotlinx.android.synthetic.main.activity_tabbed_list.*
import kotlinx.android.synthetic.main.fragment_tabbed_list.*

class TabbedListActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mFilterSectionsPagerAdapter: FilterSectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed_list)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        mFilterSectionsPagerAdapter = FilterSectionsPagerAdapter(supportFragmentManager, listOf(MonthFilterFragment(), PlaceholderFragment()))

        // Set up the ViewPager with the sections adapter.
        container.adapter = mFilterSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        fab.setOnClickListener { showPositionDialog() }
    }

    private fun showPositionDialog(){
        val fm = supportFragmentManager
        val dialog = AddItemFragmentDialog.newInstance()
        dialog.show(fm, "AddItemDialogFragment")
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class FilterSectionsPagerAdapter(fm: FragmentManager, private val sectionsList: List<Fragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return sectionsList[position]
        }

        override fun getCount(): Int {
            return sectionsList.size
        }
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val rootView = inflater.inflate(R.layout.fragment_tabbed_list, container, false)
            //rootView.section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /*private val ARG_SECTION_NUMBER = "section_number"*/
            fun newInstance(): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                /*val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args*/
                return fragment
            }
        }
    }

    class MonthFilterFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_tabbed_list, container, false)
            return rootView
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            //Set ExpandableListAdapter
            val expList = exp_monthly_list_view
            val itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java!!)
            itemViewModel.getAllFromMonth("%2018-02%").observe(this,
                    Observer<List<ItemData>> { monthItems -> expList.setAdapter(ExpandableDateAdapter(context, monthItems!!)) })
        }

        companion object {
            fun newInstance(): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                return fragment
            }
        }
    }
}
