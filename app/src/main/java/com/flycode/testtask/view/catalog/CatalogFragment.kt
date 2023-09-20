package com.flycode.testtask.view.catalog

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.flycode.testtask.utils.Factory
import com.flycode.testtask.R
import com.flycode.testtask.databinding.FragmentCatalogBinding
import com.flycode.testtask.model.bodies.SearchResults
import com.flycode.testtask.utils.DateConverter
import com.flycode.testtask.view.catalog.adapter.HotelAdapter
import com.flycode.testtask.view.catalog.view_model.CatalogViewModel
import kotlinx.coroutines.launch

private const val SEARCH_KEY = "SEARCH_KEY"
private const val COUNTRY = "COUNTRY"
private const val PERSON_COUNT = "PERSON_COUNT"
private const val DAY_COUNT = "DAY_COUNT"
private const val DATE = "DATE"

class CatalogFragment : Fragment() {

    private lateinit var mBinding: FragmentCatalogBinding

    private lateinit var adapter: HotelAdapter


    private val viewModel: CatalogViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, Factory(activity.application))
            .get(CatalogViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_catalog,
            container,
            false
        )
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.hotelList.collect {
                    updateItems(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    mBinding.catalogProgressBar.visibility = if(it) View.VISIBLE else View.GONE
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
        viewModel.fetchHotels(key = requireArguments().getString(SEARCH_KEY, ""))

        mBinding.catalogRv.layoutManager = LinearLayoutManager(requireContext())
        adapter = HotelAdapter(
            items = ArrayList()
        )

        mBinding.catalogRv.adapter = adapter

        mBinding.backIv.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        mBinding.toolbarTitleTv.text = requireArguments().getString(COUNTRY)
        val date = DateConverter.formatDateToShortMonth(DateConverter.stringToDate(requireArguments().getString(DATE)!!)!!)
        val dayCount = requireArguments().getInt(DAY_COUNT)
        val personCount = requireArguments().getInt(PERSON_COUNT)

        mBinding.toolbarDescriptionTv.text = "$date  · $dayCount ночей · $personCount чел"
    }

    private fun updateItems(list: List<SearchResults>) {
        adapter.setItems(list)
    }

    companion object {
        @JvmStatic
        fun newInstance(
            key: String,
            country: String,
            personCount: Int,
            dayCount: Int,
            date: String
        ) =
            CatalogFragment().apply {
                arguments = Bundle().apply {
                    putString(SEARCH_KEY, key)
                    putString(COUNTRY, country)
                    putInt(PERSON_COUNT, personCount)
                    putInt(DAY_COUNT, dayCount)
                    putString(DATE, date)
                }
            }
    }
}