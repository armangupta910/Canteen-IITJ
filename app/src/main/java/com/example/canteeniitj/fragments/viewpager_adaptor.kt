package com.example.canteeniitj.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class viewpager_adaptor(fragmentmanager: FragmentManager,lifecydle:Lifecycle):FragmentStateAdapter(fragmentmanager,lifecydle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                first()
            }
            1->{
                second()
            }
            2->{
                third()
            }
            else->{
                Fragment()
            }
        }
    }
}