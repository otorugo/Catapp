package com.example.basicprojects.catapp.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.setPadding

class CustomSpinnerAdapter(private val context : Context, private val tags : Array<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return tags.size
    }

    override fun getItem(p0: Int): Any {
        return tags[p0]
    }

    override fun getItemId(p0: Int): Long {
        return tags[p0].hashCode().toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val textView = TextView(context)
        textView.text = tags[p0]
        textView.setPadding(10)
        return textView
    }
}