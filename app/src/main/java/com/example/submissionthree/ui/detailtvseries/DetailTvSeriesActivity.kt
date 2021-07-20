package com.example.submissionthree.ui.detailtvseries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionthree.R
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.databinding.ActivityDetailTvSeriesBinding
import com.example.submissionthree.viewmodel.ViewModelFactory

class DetailTvSeriesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV = "extra_tv_series"
    }

    private lateinit var tvSerialEntity: EntityTvSerial
    private lateinit var binding: ActivityDetailTvSeriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(applicationContext)
        )[DetailTvSeriesViewModel::class.java]
        val seriesSelected = intent.getStringExtra(EXTRA_TV)
        if (seriesSelected != null) {
            viewModel.setSelectedTVSerial(seriesSelected)
            viewModel.getSelectedTVSerial().observe(this, {
                tvSerialEntity = it
                inflateActivity(tvSerialEntity)
                SetFloatingButtonFavorite(tvSerialEntity.bookmarked)
            })
        }
        binding.fabFavorite.setOnClickListener {
            tvSerialEntity.bookmarked = !tvSerialEntity.bookmarked
            viewModel.setTvSerialBookmark(tvSerialEntity, tvSerialEntity.bookmarked)
        }

    }

    private fun inflateActivity(entity: EntityTvSerial) {
        binding.tvMovieTitle.text = entity.title
        binding.tvReleaseDate.text = entity.releaseDate
        binding.tvMovieScore.text = entity.score
        binding.tvMovieDesc.text = entity.description
        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + entity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imagePoster)
    }

    private fun SetFloatingButtonFavorite(state: Boolean) {
        val floatButton = binding.fabFavorite
        if (state) {
            floatButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            floatButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )

        }
    }
}