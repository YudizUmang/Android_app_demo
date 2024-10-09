package com.example.android_app_demo.viewpager_tab.recipe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecipeAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val itemCount = 3

    override fun getItemCount(): Int {
        return 200
    }


    override fun createFragment(position: Int): Fragment {

        val realPosition = position % itemCount
        return when (realPosition) {
            0 -> ItalianFragment()
            1 -> SouthIndianFragment()
            2 -> GujaratiFragment()

            else -> throw IllegalStateException("Invalid position: $realPosition")
        }
    }
}
