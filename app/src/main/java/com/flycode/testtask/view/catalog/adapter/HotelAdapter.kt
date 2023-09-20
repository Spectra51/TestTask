package com.flycode.testtask.view.catalog.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Html
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.flycode.testtask.R
import com.flycode.testtask.databinding.ItemHotelBinding
import com.flycode.testtask.model.bodies.SearchResults
import com.flycode.testtask.utils.DateConverter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Date


class HotelAdapter(val items:ArrayList<SearchResults>) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemHotelBinding>(layoutInflater,
                R.layout.item_hotel, parent, false)
        return HotelViewHolder(binding)
    }

    fun setItems(list:List<SearchResults>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(item = items[position])
    }

    inner class HotelViewHolder(val binding: ItemHotelBinding) : ViewHolder(binding.root) {
        fun bind(item:SearchResults){

            Glide
                .with(binding.root)
                .load(R.drawable.image)
                .into(binding.hotelIv)


            binding.minPriceTv.text = "от ${item.offer?.price?.total ?: 0} ₽ за ${item.offer?.adults ?: 0}-x"
            binding.titleTv.text = item.offer?.title ?: "Title not found"
            val startDate = DateConverter.formatDateToHotel(DateConverter.stringToDate(item.offer?.startDate!!)!!)
            val endDate = DateConverter.formatDateToHotel(
                Date(DateConverter.stringToDate(item.offer?.startDate!!)!!.time + item.offer!!.duration!!*24*60*60*1000))
            binding.tagCg.removeAllViews()
            createChipGroup(binding.tagCg, list = listOf(
                "1 линия",
                "200м до пляжа",
                "Бесплатный Wi-Fi",
            ))
            val duration = item.offer?.duration ?: 0
            val text = itemView.resources.getString(R.string.trip_info,startDate,endDate,duration)
            val spannableString = SpannableString(text)
            val color = Color.parseColor("#FF272B30")
            spannableString.setSpan(ForegroundColorSpan(color),text.indexOf(startDate),text.indexOf(startDate) + startDate.length,0)
            spannableString.setSpan(StyleSpan(Typeface.BOLD),text.indexOf(startDate),text.indexOf(startDate) + startDate.length,0)
            spannableString.setSpan(ForegroundColorSpan(color),text.indexOf(endDate),text.indexOf(endDate) + endDate.length,0)
            spannableString.setSpan(StyleSpan(Typeface.BOLD),text.indexOf(endDate),text.indexOf(endDate) + endDate.length,0)
            spannableString.setSpan(ForegroundColorSpan(color),text.lastIndexOf(duration.toString()),text.lastIndexOf(duration.toString()) + duration.toString().length,0)
            spannableString.setSpan(StyleSpan(Typeface.BOLD),text.lastIndexOf(duration.toString()),text.lastIndexOf(duration.toString()) + duration.toString().length,0)
            binding.tripInfoTv.text = spannableString
        }

        private fun createChipGroup(container:ChipGroup,list:List<String>){
            list.forEach {
                val mChip =
                    LayoutInflater.from(itemView.context).inflate(R.layout.chip_tag, null, false) as Chip
                mChip.text = it
                container.addView(mChip)
            }
        }
    }
}