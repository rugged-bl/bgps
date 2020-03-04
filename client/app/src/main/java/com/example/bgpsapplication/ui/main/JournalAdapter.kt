package com.example.bgpsapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bgpsapplication.R

class JournalAdapter : ListAdapter<JournalRecordCollapsed, JournalRecordViewHolder>(Callback()) {
    class Callback : DiffUtil.ItemCallback<JournalRecordCollapsed>() {
        override fun areItemsTheSame(oldItem: JournalRecordCollapsed, newItem: JournalRecordCollapsed): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: JournalRecordCollapsed, newItem: JournalRecordCollapsed): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalRecordViewHolder {
        return JournalRecordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.journal_record, parent, false)
        )
    }

    override fun onBindViewHolder(holder: JournalRecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}