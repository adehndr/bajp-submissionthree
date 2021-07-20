package com.example.submissionthree.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionthree.databinding.FragmentFavoriteTvSeriesBinding
import com.example.submissionthree.ui.tvseries.TVSeriesAdapter
import com.example.submissionthree.viewmodel.ViewModelFactory


class FavoriteTvSeriesFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteTvSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            val serialAdapter = TVSeriesAdapter()
            viewModel.getTvSeries().observe(this,
                {
                    serialAdapter.submitList(it)
                    serialAdapter.notifyDataSetChanged()
                })

            with(binding.rvSeries)
            {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = serialAdapter
            }
        }
    }


}