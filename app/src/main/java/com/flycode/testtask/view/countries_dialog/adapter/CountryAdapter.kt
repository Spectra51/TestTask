package com.flycode.testtask.view.countries_dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.flycode.testtask.R
import com.flycode.testtask.databinding.ItemStringBinding

class CountryAdapter(val list: ArrayList<String>, val callback: (String) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemStringBinding>(layoutInflater,
                R.layout.item_string, parent, false)
        return CountryViewHolder(binding)
    }

    fun setItems(items: List<String>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CountryViewHolder(val binding: ItemStringBinding) : ViewHolder(binding.root) {

        lateinit var currentItem: String

        init {
            binding.container.setOnClickListener {
                callback.invoke(currentItem)
            }
        }

        fun bind(item: String) {
            currentItem = item
            binding.textView.text = item

        }
    }
}