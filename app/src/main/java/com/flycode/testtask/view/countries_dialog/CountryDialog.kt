package com.flycode.testtask.view.countries_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.flycode.testtask.utils.Factory
import com.flycode.testtask.view.countries_dialog.adapter.CountryAdapter
import com.flycode.testtask.R
import com.flycode.testtask.databinding.BottomDialogCountryBinding
import com.flycode.testtask.view.choosing_date.PLACE_FROM_WHERE_REQUEST
import com.flycode.testtask.view.choosing_date.PLACE_TO_WHERE_REQUEST
import com.flycode.testtask.view.countries_dialog.view_model.CountryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

private const val SCREEN_TYPE = "SCREEN_TYPE"

class CountryDialog() : BottomSheetDialogFragment() {

    private lateinit var screenType: ScreenType

    private val viewModel: CountryViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, Factory(activity.application))
            .get(CountryViewModel::class.java)
    }

    private lateinit var mBinding: BottomDialogCountryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.bottom_dialog_country,
            container,
            false
        )
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenType =
            if (requireArguments().getInt(SCREEN_TYPE) == 0) {
                viewModel.fetchCountries()
                ScreenType.Country
            } else {
                viewModel.fetchCities()
                ScreenType.City
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initRv(list: List<String>) {
        val adapter = CountryAdapter(list = ArrayList(list), callback = {
            when(screenType){
                ScreenType.City -> {
                    parentFragmentManager.setFragmentResult(PLACE_FROM_WHERE_REQUEST, bundleOf("fromWhere" to it))
                    this.dismiss()
                }
                ScreenType.Country -> {
                    parentFragmentManager.setFragmentResult(PLACE_TO_WHERE_REQUEST, bundleOf("toWhere" to it))
                    this.dismiss()
                }
            }
        })
        mBinding.countryRv.layoutManager = LinearLayoutManager(requireContext())
        mBinding.countryRv.adapter = adapter
        adapter.setItems(list)
        mBinding.searchEt.addTextChangedListener { query ->
            adapter.setItems(list.filter {
                it.contains(query.toString(), true)
            })
        }
    }

    fun initView() {
        when (screenType) {
            ScreenType.City -> {
                initLikeCityPicker()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.cityList.collect {
                            if (it.isNotEmpty()) {
                                initRv(list = it.mapNotNull {
                                    it.name
                                })
                            }
                        }
                    }
                }
            }

            ScreenType.Country -> {
                initLikeCountryPicker()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.countryList.collect {
                            if (it.isNotEmpty()) {
                                initRv(list = it.mapNotNull {
                                    it.name
                                })
                            }
                        }
                    }
                }
            }

            else -> {}
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isError.collect {
                    if (it.second) {
                        viewModel.errorShown()
                        Toast.makeText(requireContext(), it.first, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        mBinding.closeTv.setOnClickListener {
            this.dismiss()
        }
    }

    fun initLikeCityPicker(){
        mBinding.searchEt.hint = resources.getString(R.string.city_picker)
    }
    fun initLikeCountryPicker(){
        mBinding.searchEt.hint = resources.getString(R.string.country_picker)
    }

    companion object {
        @JvmStatic
        fun newInstance(type: ScreenType) =
            CountryDialog().apply {
                arguments = Bundle().apply {
                    putInt(SCREEN_TYPE, type.id)
                }
            }
    }
}

sealed class ScreenType(val id: Int) {
    object Country : ScreenType(0)
    object City : ScreenType(1)
}