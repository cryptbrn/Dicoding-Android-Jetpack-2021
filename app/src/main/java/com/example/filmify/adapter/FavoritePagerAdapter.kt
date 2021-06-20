package com.example.filmify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmify.ui.fragment.FavMovieFragment
import com.example.filmify.ui.fragment.FavTvShowFragment


class FavoritePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                FavMovieFragment()
            }
            1->{
                FavTvShowFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}