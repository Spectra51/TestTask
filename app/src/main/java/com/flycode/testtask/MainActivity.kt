package com.flycode.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.flycode.testtask.view.choosing_date.ChoosingDateFragment
import com.flycode.testtask.databinding.ActivityMainBinding
import com.flycode.testtask.view.BlankFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ChoosingDateFragment.newInstance(), "ChoosingData")
            .commit()
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, ChoosingDateFragment.newInstance(), "ChoosingData")
                        .commit()
                    true
                }
                R.id.menu_item2 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, BlankFragment.newInstance(), "ChoosingData")
                        .commit()
                    true
                }
                R.id.menu_item3 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, BlankFragment.newInstance(), "ChoosingData")
                        .commit()
                    true
                }
                R.id.menu_item4 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, BlankFragment.newInstance(), "ChoosingData")
                        .commit()
                    true
                }

                else -> {
                    true
                }
            }
        }
    }
}