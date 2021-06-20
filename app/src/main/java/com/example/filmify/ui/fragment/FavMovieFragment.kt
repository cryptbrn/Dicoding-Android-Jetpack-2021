package com.example.filmify.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmify.adapter.MoviesAdapter
import com.example.filmify.databinding.FragmentFavMovieBinding
import com.example.filmify.databinding.FragmentMoviesBinding
import com.example.filmify.ui.FavoriteActivity
import com.example.filmify.ui.MainActivity
import com.example.filmify.ui.viewModel.FavoriteViewModel
import com.example.filmify.ui.viewModel.MoviesViewModel
import com.example.filmify.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavMovieFragment : Fragment() {
    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesAdapter: MoviesAdapter
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
            moviesAdapter = MoviesAdapter()
            moviesResponse()
            with(binding.rvFavMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private fun moviesResponse() {
        viewModel.movies.observe(viewLifecycleOwner, {
            if(!EspressoIdlingResource.idlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
            showProgress(false)
            moviesAdapter.setMovies(it)
            moviesAdapter.notifyDataSetChanged()
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