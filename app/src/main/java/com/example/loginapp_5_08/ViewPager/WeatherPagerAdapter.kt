package com.example.loginapp_5_08.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.loginapp_5_08.homeScreen.HomeFragment

private const val MAX_VALUE = 200

// 1
class WeatherPagerAdapter(fragmentManager: FragmentManager, private val movies: ArrayList<Cities>, val s:Int) :
    FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        //??
        //return HomeFragment.newInstance(movies[position % movies.size])
        return HomeFragment.newInstance("Hello", s)
    }

    // 3
    override fun getCount(): Int {
        return movies.size
    }
}