package com.example.filmify.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmify.adapter.TvShowsAdapter
import com.example.filmify.databinding.FragmentTvShowsBinding
import com.example.filmify.ui.MainActivity
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
            showProgress(true)
            getTvShows()
            tvShowsAdapter = TvShowsAdapter()
            tvShowsResponse()
            with(binding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    private fun getTvShows(){
        if((activity as MainActivity).getConnectionType()){
            viewModel.getTvShows()
        }
        else {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Internet Connection Lost")
            builder.setPositiveButton("REFRESH"){ _, _ ->
                getTvShows()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }


    private fun tvShowsResponse() {
        viewModel.tvShows.observe(viewLifecycleOwner, {
            if (it.success) {
                showProgress(false)
                tvShowsAdapter.setTvShows(it.results)
                tvShowsAdapter.notifyDataSetChanged()
            } else {
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
        binding.tvShowsProgress.bringToFront()
        binding.tvShowsProgress.visibility = if(show) View.VISIBLE else View.GONE
        if(activity!=null) (activity as MainActivity).disableTouch(show)
    }

}