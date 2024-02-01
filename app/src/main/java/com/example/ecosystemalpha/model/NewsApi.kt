package com.example.ecosystemalpha.model

interface NewsApi {
    suspend fun getData(): List<NewsModel>
}