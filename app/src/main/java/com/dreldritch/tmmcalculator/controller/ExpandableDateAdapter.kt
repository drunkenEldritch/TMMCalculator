package com.dreldritch.tmmcalculator.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.dreldritch.tmmcalculator.R
import com.dreldritch.tmmcalculator.model.roomdb.ItemData
import java.text.DecimalFormat

/**
 * Created by cerox on 25.02.2018.
 */
class ExpandableDateAdapter(val context: Context, val itemlist: List<ItemData>): BaseExpandableListAdapter() {

    var dateset = mutableListOf<String>()
    var dayItemMap = mutableMapOf<String, List<ItemData>>()

    init {
        itemlist.distinctBy { it.date }
                .forEach { i -> dateset.add(i.date)}

        dateset.sort()

        for(x in dateset)
            dayItemMap.put(x, itemlist.filter { it.date == x })
    }

    override fun getGroup(groupPosition: Int): Any {
        return dateset[groupPosition]
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inf = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inf.inflate(R.layout.month_group_heading, null)
        }

        val dayheading = view!!.findViewById(R.id.date_group_heading_txt) as TextView
        dayheading.text = dateset[groupPosition]

        val sumPrizeTxt = view!!.findViewById(R.id.sum_prize_txt) as TextView
        val prize = DecimalFormat("#0.00").format(dayItemMap.get(dateset[groupPosition])
                ?.sumByDouble { itemData ->  itemData.price })
        sumPrizeTxt.text = prize.toString()

        return view
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return dateset.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return dayItemMap[dateset[groupPosition]]!!.get(childPosition)
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val itemInfo = getChild(groupPosition, childPosition) as ItemData

        if (view == null) {
            val infalInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = infalInflater.inflate(R.layout.item_info_child, null)
        }

        val itemName = view!!.findViewById(R.id.item_name_txt) as TextView
        itemName.text = itemInfo.name

        val itemPrice = view!!.findViewById(R.id.item_price_txt) as TextView
        itemPrice.text = DecimalFormat("#0.00").format(itemInfo.price)

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildrenCount(groupPosition: Int): Int {
         return dayItemMap[dateset[groupPosition]]!!.size
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}