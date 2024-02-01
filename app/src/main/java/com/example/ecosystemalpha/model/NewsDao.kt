package com.example.ecosystemalpha.model

import androidx.lifecycle.MutableLiveData

interface NewsDao {
    fun getData(): MutableLiveData<List<NewsModel>>
    fun saveData(news: List<NewsModel>)
}