package com.example.submissionthree.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionthree.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sectionPagerAdapter = SectionPagerFavoriteAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }

    private fun setActionBarTitle() {
        supportActionBar?.title = "Favorite Page"
    }
}