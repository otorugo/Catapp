package com.example.basicprojects.catapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicprojects.catapp.spinner.CustomSpinnerAdapter
import com.example.basicprojects.databinding.FragmentCatAppBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatApp : Fragment() {

    private lateinit var viewModel: CatAppViewModel

    private var _binding: FragmentCatAppBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CatAppViewModel::class.java]
        _binding = FragmentCatAppBinding.inflate(inflater, container, false)


        val call = theCatAppService.getCatTags()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("CatApp", "Failed to get tags")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    binding.imageResult.text = response.body()
                }
            }
        })


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