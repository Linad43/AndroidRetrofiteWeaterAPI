package com.example.androidretrofiteweaterapi.servise

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidretrofiteweaterapi.data.ItemsPage
import com.example.androidretrofiteweaterapi.fragments.ViewPageFragment


class ViewPagerAdapter(
    fragment: FragmentActivity,
    private val viewPagerList:ArrayList<ItemsPage>
): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return viewPagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPageFragment()
        fragment.arguments = bundleOf("vp" to viewPagerList[position])
        return fragment
    }
}