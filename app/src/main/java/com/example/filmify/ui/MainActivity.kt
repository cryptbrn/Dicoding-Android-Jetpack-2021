package com.example.filmify.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.filmify.R
import com.example.filmify.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager=findViewById<ViewPager2>(R.id.view_pager)
        val adapter= HomePagerAdapter(supportFragmentManager,lifecycle)

        viewPager.adapter=adapter

        TabLayoutMediator(tabLayout,viewPager){tab,position->
            when(position){
                0->{
                    tab.text=getString(R.string.movies)
                }
                1->{
                    tab.text=getString(R.string.tv_shows)
                }
            }
        }.attach()
    }
}