package com.example.basicprojects.catapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.setPadding
import com.example.basicprojects.R
import com.example.basicprojects.catapp.spinner.CustomSpinnerAdapter
import com.example.basicprojects.databinding.FragmentCatAppBinding

class CatApp : Fragment() {

    private lateinit var viewModel: CatAppViewModel

    private var _binding: FragmentCatAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CatAppViewModel::class.java]
        _binding = FragmentCatAppBinding.inflate(inflater, container, false)

        val view = binding.root
        binding.catappSpinner.adapter =
            CustomSpinnerAdapter(requireContext(), arrayOf("new", "old", "future"))

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}