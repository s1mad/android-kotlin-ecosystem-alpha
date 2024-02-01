package com.example.ecosystemalpha.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecosystemalpha.model.NewsModel
import com.example.ecosystemalpha.model.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private var _newsLiveData : MutableLiveData<List<NewsModel>> = newsRepository.data
    val newsLiveData: LiveData<List<NewsModel>> = _newsLiveData

    init {
        fetchData()
    }

    fun fetchData(){
        viewModelScope.launch(Dispatchers.Main){
            try {
                newsRepository.refresh()
            } catch (e: UnknownHostException){
                Log.e("MyLog", e.message.toString())
                throw e
            } catch (e: Exception){
                Log.e("MyLog", e.message.toString())
                throw e
            }
        }
    }
}