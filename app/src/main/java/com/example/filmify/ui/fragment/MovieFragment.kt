package com.example.filmify.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmify.adapter.MoviesAdapter
import com.example.filmify.databinding.FragmentMoviesBinding
import com.example.filmify.ui.MainActivity
import com.example.filmify.ui.viewModel.MoviesViewModel
import com.example.filmify.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            viewModel = (activity as MainActivity).getMoviesViewModels()
            showProgress(true)
            getMovies()
            moviesAdapter = MoviesAdapter()
            moviesResponse()
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private fun getMovies(){
        if((activity as MainActivity).getConnectionType()){
            EspressoIdlingResource.increment()
            viewModel.getMovies()
        }
        else {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Internet Connection Lost")
            builder.setPositiveButton("REFRESH"){ _, _ ->
                getMovies()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun moviesResponse() {
        viewModel.movies.observe(viewLifecycleOwner, {
            if(!EspressoIdlingResource.idlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
            if(it.success){
                showProgress(false)
                moviesAdapter.setMovies(it.results)
                moviesAdapter.notifyDataSetChanged()
            }
            else{
                showProgress(false)
                Toast.makeText(context, "Can't load data from internet", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showProgress(show: Boolean){
        binding.moviesProgress.bringToFront()
        binding.moviesProgress.visibility = if(show) View.VISIBLE else View.GONE
        if(activity!=null) (activity as MainActivity).disableTouch(show)
    }

}