package com.example.basicprojects.catapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicprojects.R
import com.example.basicprojects.databinding.FragmentCatAppBinding

class CatApp : Fragment() {

    private lateinit var viewModel: CatAppViewModel

    private var _binding : FragmentCatAppBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CatAppViewModel::class.java)
        _binding = FragmentCatAppBinding.inflate(inflater,container,false)

        


        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}