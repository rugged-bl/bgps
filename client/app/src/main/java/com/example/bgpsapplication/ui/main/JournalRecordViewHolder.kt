package com.example.bgpsapplication.ui.main

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bgpsapplication.databinding.JournalRecordBinding

class JournalRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: JournalRecordBinding = JournalRecordBinding.bind(itemView)

    fun bind(journalRecord: JournalRecordCollapsed) {
        binding.ordinal.text = adapterPosition.toString()
        binding.fio.text = journalRecord.studentFullName
        binding.markPrIs.text =
            journalRecord.markPrIs + if (journalRecord.countPrIs == 0) "" else " П: ${journalRecord.countPrIs}"
        binding.markSii.text =
            journalRecord.markSii + if (journalRecord.countSii == 0) "" else " П: ${journalRecord.countSii}"

        binding.markPrIs.setBackgroundColor(
            if (journalRecord.markPrIs.startsWith("2")) {
                Color.RED
            } else {
                Color.TRANSPARENT
            }
        )

        binding.markSii.setBackgroundColor(
            if (journalRecord.markSii.startsWith("2")) {
                Color.RED
            } else {
                Color.TRANSPARENT
            }
        )
    }
}