package com.example.filmify.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmify.adapter.MoviesAdapter
import com.example.filmify.adapter.TvShowsAdapter
import com.example.filmify.databinding.FragmentTvShowsBinding
import com.example.filmify.ui.MainActivity
import com.example.filmify.ui.viewModel.MoviesViewModel
import com.example.filmify.ui.viewModel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvShowsAdapter: TvShowsAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = (activity as MainActivity).getTvShowsViewModels()
            viewModel.getTvShows()
            tvShowsAdapter = TvShowsAdapter()
            tvShowsResponse()
            with(binding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    private fun tvShowsResponse() {
        viewModel.tvShows.observe(viewLifecycleOwner, Observer {
            if(it.success){
                tvShowsAdapter.setTvShows(it.results)
                tvShowsAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}