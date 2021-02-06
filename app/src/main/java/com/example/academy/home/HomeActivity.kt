package com.example.academy.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.academy.R
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val viewpager = findViewById<ViewPager>(R.id.view_pagerr)
        val tab = findViewById<TabLayout>(R.id.tabs)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewpager.adapter = sectionsPagerAdapter
        tab.setupWithViewPager(viewpager)
        supportActionBar?.elevation = 0f
    }
}