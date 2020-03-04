package com.example.bgpsapplication.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bgpsapplication.R
import com.example.bgpsapplication.databinding.JournalFragmentBinding
import kotlin.properties.Delegates

class JournalFragment : Fragment() {

    companion object {
        fun newInstance() = JournalFragment()
    }

    private val viewModel by activityViewModels<JournalViewModel>()

    private lateinit var binding: JournalFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = JournalFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.header) {
            ordinal.text = "#"
            fio.text = "ФИО"
            markPrIs.text = "ПрИС"
            markSii.text = "СИИ"
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.onGroupSelected(viewModel.studyGroups.value?.getOrNull(position))
            }
        }

        val recyclerAdapter = JournalAdapter()

        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        viewModel.journalRecords.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.submitList(it)
        })

        viewModel.studyGroups.observe(viewLifecycleOwner, Observer {
            binding.spinner.adapter =
                ArrayAdapter(requireContext(), R.layout.study_group_item, R.id.group_name, it.map { it.name })
        })
    }
}
