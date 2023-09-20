package com.flycode.testtask.view.choosing_date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.flycode.testtask.utils.Factory
import com.flycode.testtask.R
import com.flycode.testtask.databinding.FragmentChoosingDateBinding
import com.flycode.testtask.view.catalog.CatalogFragment
import com.flycode.testtask.view.choosing_date.view_model.ChoosingDateViewModel
import com.flycode.testtask.view.countries_dialog.CountryDialog
import com.flycode.testtask.view.countries_dialog.ScreenType
import kotlinx.coroutines.launch

const val PLACE_FROM_WHERE_REQUEST = "PLACE_FROM_WHERE_REQUEST"
const val PLACE_TO_WHERE_REQUEST = "PLACE_TO_WHERE_REQUEST"

class ChoosingDateFragment : Fragment() {

    private lateinit var mBinding: FragmentChoosingDateBinding

    private val viewModel: ChoosingDateViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, Factory(activity.application))
            .get(ChoosingDateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_choosing_date,
            container,
            false
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchKey.collect {
                    if (!it.first.isNullOrEmpty() && !it.second) {
                        viewModel.navigationDone()
                        parentFragmentManager
                            .beginTransaction()
                            .replace(
                                R.id.fragment_container, CatalogFragment.newInstance(
                                    key = it.first!!,
                                    country = mBinding.toWhereTv.text.toString(),
                                    personCount = 2,
                                    dayCount = 10,
                                    date = "2023-08-15"
                                ), "Catalog"
                            )
                            .addToBackStack("Choosing")
                            .commit()
                    }
                }
            }
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

        mBinding.fromWhereTv.setOnClickListener {
            CountryDialog.newInstance(type = ScreenType.City).show(parentFragmentManager, "dialog")
        }
        mBinding.toWhereTv.setOnClickListener {
            CountryDialog.newInstance(type = ScreenType.Country)
                .show(parentFragmentManager, "dialog")
        }
        mBinding.searchBtn.setOnClickListener {
            if (mBinding.fromWhereTv.text.contains("откуда",true)){
                Toast.makeText(requireContext(), "Выберите откуда", Toast.LENGTH_SHORT).show()
            }else if(mBinding.toWhereTv.text.contains("куда",true)){
                Toast.makeText(requireContext(), "Выберите куда", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.fetchSearchKey(
                    startFrom = "2023-08-15",
                    startTo = "2023-09-15",
                    durationFrom = 13,
                    durationTo = 17,
                    adults = 2,
                    kids = 0,
                    departCityId = 20001,
                    regionIds = ArrayList(
                        listOf(
                            1644
                        )
                    )
                )
            }
        }

        parentFragmentManager.setFragmentResultListener(
            PLACE_FROM_WHERE_REQUEST,
            viewLifecycleOwner
        ) { _, bundle ->
            mBinding.fromWhereTv.text = bundle.getString("fromWhere")
        }
        parentFragmentManager.setFragmentResultListener(
            PLACE_TO_WHERE_REQUEST,
            viewLifecycleOwner
        ) { _, bundle ->
            mBinding.toWhereTv.text = bundle.getString("toWhere")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChoosingDateFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}