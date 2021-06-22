package com.example.filmify.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmify.adapter.FavoritePagedListAdapter
import com.example.filmify.databinding.FragmentFavMovieBinding
import com.example.filmify.ui.FavoriteActivity
import com.example.filmify.ui.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavMovieFragment : Fragment() {
    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesListAdapter: FavoritePagedListAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            viewModel = (activity as FavoriteActivity).getFavViewModels()
            showProgress(true)
            moviesListAdapter = FavoritePagedListAdapter()
            moviesResponse()
            with(binding.rvFavMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesListAdapter
            }
        }
    }

    private fun moviesResponse() {
        viewModel.getSavedMovies().observe(viewLifecycleOwner, {
            showProgress(false)
            moviesListAdapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showProgress(show: Boolean){
        binding.moviesProgress.bringToFront()
        binding.moviesProgress.visibility = if(show) View.VISIBLE else View.GONE
        if(activity!=null) (activity as FavoriteActivity).disableTouch(show)
    }

}