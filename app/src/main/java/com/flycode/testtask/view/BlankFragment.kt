package com.flycode.testtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flycode.testtask.R


class BlankFragment : Fragment(R.layout.fragment_blank) {

    companion object {
        @JvmStatic
        fun newInstance() =
            BlankFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}