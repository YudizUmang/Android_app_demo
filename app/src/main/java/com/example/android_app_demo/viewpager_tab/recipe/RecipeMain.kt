package com.example.android_app_demo.viewpager_tab.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.android_app_demo.R
import com.google.android.material.tabs.TabLayout

class RecipeMain : AppCompatActivity() {
//    private val cuisineArray = arrayOf(
//        "Italian ",
//        "South Indian",
//        "Gujarati",
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        var indicator = 0
        //Log.d("Item", viewPager.currentItem.toString())


        val adapter = RecipeAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        viewPager.setOffscreenPageLimit(2)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                indicator = when (viewPager.currentItem) {
                    0 -> 0
                    1 -> 1
                    2 -> 2
                    else -> viewPager.currentItem % 3
                }

                tabLayout.selectTab(tabLayout.getTabAt(indicator))
            }
        })
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager.setCurrentItem(tab.position)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//            }
//        })


        viewPager.setCurrentItem(102, true)

//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = cuisineArray[position % cuisineArray.size]
//        }.attach()


    }
}
