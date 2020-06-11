package com.activevideo.keytest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KeyHistoryAdapter (val items: MutableList<String>) : RecyclerView.Adapter<KeyHistoryAdapter.KeyHistoryHolder> () {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KeyHistoryAdapter.KeyHistoryHolder {
        return KeyHistoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_key_event, parent, false) as TextView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: KeyHistoryHolder, position: Int) {
        holder.textView.text = items[position]
    }

    inner class KeyHistoryHolder (val textView: TextView) : RecyclerView.ViewHolder(textView)
}