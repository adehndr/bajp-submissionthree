package com.example.submissionthree.ui.tvseries

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionthree.R
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.databinding.ItemsBinding
import com.example.submissionthree.ui.detailtvseries.DetailTvSeriesActivity

class TVSeriesAdapter :
    PagedListAdapter<EntityTvSerial, TVSeriesAdapter.SeriesViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityTvSerial>() {
            override fun areItemsTheSame(
                oldItem: EntityTvSerial,
                newItem: EntityTvSerial
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: EntityTvSerial,
                newItem: EntityTvSerial
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    class SeriesViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(serial: EntityTvSerial) {
            with(binding)
            {
                tvMovieTitle.text = serial.title
                tvReleaseDate.text = serial.releaseDate
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvSeriesActivity::class.java)
                    intent.putExtra(DetailTvSeriesActivity.EXTRA_TV, serial.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + serial.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_24)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val itemsSeriesBinding =
            ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(itemsSeriesBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val tvSerial = getItem(position)
        if (tvSerial != null) {
            holder.bind(tvSerial)
        }
    }


}