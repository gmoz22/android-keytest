package com.activevideo.keytest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KeyHistoryFragment : Fragment () {

    private lateinit var recyclerHistory: RecyclerView
    private var adapter: KeyHistoryAdapter = KeyHistoryAdapter(mutableListOf())
    private lateinit var tvHistoryTitle: TextView
//    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_key_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerHistory = view.findViewById<RecyclerView>(R.id.recycler_history).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@KeyHistoryFragment.adapter
        }
        tvHistoryTitle = view.findViewById(R.id.text_history_title)
        tvHistoryTitle.text = "HISTORY"

//        button.setOnClickListener { }
    }

    fun addEventItem(keyEvent: String) {
        adapter.items.add(0, keyEvent)
        adapter.notifyDataSetChanged()
        recyclerHistory.smoothScrollToPosition(0)
//        recyclerHistory.smoothScrollToPosition(adapter.itemCount-1)
    }

}