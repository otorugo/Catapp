package com.example.basicprojects.catapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.example.basicprojects.catapp.spinner.CustomSpinnerAdapter
import com.example.basicprojects.catapp.spinner.ImageInfo
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

        val imageInfo = ImageInfo()

        binding.catInputPhrase.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                imageInfo.text = s.toString()
            }
        })


        val call = theCatAppService.getCatTags()
        call.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("CatApp", "Failed to get tags")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let {


                        val responseResult = it.filter { name -> name.isNotEmpty() }
                        binding.catappSpinner.adapter =
                            CustomSpinnerAdapter(requireContext(), responseResult)

                    }
                }
            }
        })

        binding.catappSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                imageInfo.tag = parent?.getItemAtPosition(position) as String?
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        binding.generateButton.setOnClickListener {
            val base_url = "https://cataas.com/cat/${imageInfo.tag}/says/${imageInfo.text}"
            Log.i("Glide", "Entered Here")
            Glide.with(requireContext()).load(base_url)
                .into(binding.imageResult)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}