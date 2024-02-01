package com.example.ecosystemalpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecosystemalpha.adapter.NewsItemAdapter
import com.example.ecosystemalpha.databinding.ActivityMainBinding
import com.example.ecosystemalpha.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsItemAdapter: NewsItemAdapter
    private val newsViewModel by viewModel<NewsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsItemAdapter = NewsItemAdapter(this, emptyList())
        binding.newsRecyclerView.adapter = newsItemAdapter

        newsViewModel.newsLiveData.observe(this) {
            newsItemAdapter.updateItemList(it)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            newsViewModel.fetchData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}