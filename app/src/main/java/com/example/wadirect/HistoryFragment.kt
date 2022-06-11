package com.example.wadirect

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HistoryFragment : Fragment() {

    private lateinit var view1:View
    lateinit var textView: TextView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var recyclerView: RecyclerView
    var messages: MutableList<String> = mutableListOf()
    var numbers: MutableList<String> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_history, container, false)
        return view1
    }

    override fun onResume() {
        super.onResume()
        addView(view1)
    }

    private fun addView(views: View?) {

        recyclerView = views?.findViewById(R.id.recycler_view) as RecyclerView

        Log.d("NUMB2", "addView: $numbers")
        Log.d("NUMB1", "addView: ${HomeFragment.data}")
        recyclerView.layoutManager = LinearLayoutManager(view1.context)
        recyclerAdapter = RecyclerAdapter(HomeFragment.data,view1.context)
        recyclerView.adapter = recyclerAdapter

    }

}