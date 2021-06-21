package com.example.filmify.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmify.adapter.FavoritePagedListAdapter
import com.example.filmify.databinding.FragmentFavTvShowBinding
import com.example.filmify.ui.FavoriteActivity
import com.example.filmify.ui.viewModel.FavoriteViewModel
import com.example.filmify.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavTvShowFragment : Fragment() {
    private var _binding: FragmentFavTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvShowsAdapter: FavoritePagedListAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = (activity as FavoriteActivity).getFavViewModels()
            showProgress(true)
            tvShowsAdapter = FavoritePagedListAdapter()
            tvShowsResponse()
            with(binding.rvFavTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    private fun tvShowsResponse() {
        viewModel.getSavedTvShows().observe(viewLifecycleOwner, {
            if(!EspressoIdlingResource.idlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
            showProgress(false)
            tvShowsAdapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showProgress(show: Boolean){
        binding.tvShowsProgress.bringToFront()
        binding.tvShowsProgress.visibility = if(show) View.VISIBLE else View.GONE
        if(activity!=null) (activity as FavoriteActivity).disableTouch(show)
    }

}