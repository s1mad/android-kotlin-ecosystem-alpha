package com.example.ecosystemalpha.model


class NewsRepository(private val newsApi: NewsApi, private val newsDao: NewsDao) {
    var data = newsDao.getData()

    suspend fun refresh() {
        val newData = newsApi.getData()
        newsDao.saveData(newData)
        data.postValue(newsDao.getData().value)
    }
}