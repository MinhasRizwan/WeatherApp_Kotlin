package com.example.loginapp_5_08.homeScreen.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.loginapp_5_08.homeScreen.HomeFragment
import com.example.loginapp_5_08.shared.SharedPreference

class WeatherPagerAdapter(fragmentManager: FragmentManager,  val cities:ArrayList<String>, val lati:Double, val longi:Double, val sharedPreference: SharedPreference) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return HomeFragment.newInstance( cities[position], lati, longi,sharedPreference)
    }

    override fun getCount(): Int {
        return cities.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return cities[position]
    }

}