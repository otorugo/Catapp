package com.example.basicprojects.catapp.spinner

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.setPadding
import com.bumptech.glide.Glide


class CustomSpinnerAdapter(private val context: Context, private val tags: List<String>) :
    BaseAdapter() {

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
        textView.setPadding(20)
        return textView
    }
}

data class ImageInfo(var tag : String? = null, var text : String? = null)


class SelectSpinner(val context: Context,val imageInfo : ImageInfo) : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.let {
            imageInfo.tag = it.getItemAtPosition(position) as String?
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.i("CustomSpinner","Nothing was selected")
    }
}




