package com.example.submissionthree.ui.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionthree.databinding.FragmentTVSeriesBinding
import com.example.submissionthree.viewmodel.ViewModelFactory
import com.example.submissionthree.vo.Status


class TVSeriesFragment : Fragment() {
    private lateinit var fragmentSeriesBinding: FragmentTVSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSeriesBinding = FragmentTVSeriesBinding.inflate(layoutInflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(this, factory)[TVSeriesViewModel::class.java]
            val serialAdapter = TVSeriesAdapter()
            viewModel.getSeries().observe(this, { tvSerial ->
                if (tvSerial != null) {
                    when (tvSerial.status) {
                        Status.LOADING -> fragmentSeriesBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentSeriesBinding.progressBar.visibility = View.GONE
                            serialAdapter.submitList(tvSerial.data)
                            serialAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentSeriesBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        }

                    }
                }

            })


            with(fragmentSeriesBinding.rvSeries)
            {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = serialAdapter
            }
        }
    }
}