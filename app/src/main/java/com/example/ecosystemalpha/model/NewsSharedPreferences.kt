package com.example.ecosystemalpha.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val PREFERENCE_FILE_KEY = "EcosystemAlpha"
private const val PREFERENCE_NEWS_MODEL_KEY = "NewsModel"
class NewsSharedPreferences(context: Context): NewsDao {
    private val sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
    override fun getData(): MutableLiveData<List<NewsModel>> {
        val json = sharedPreferences.getString(PREFERENCE_NEWS_MODEL_KEY, null)
        val type = object : TypeToken<List<NewsModel>>() {}.type
        val newsList = Gson().fromJson<List<NewsModel>>(json, type)
        return MutableLiveData(newsList ?: emptyList())
    }

    override fun saveData(news: List<NewsModel>) {
        with(sharedPreferences.edit()){
            putString(PREFERENCE_NEWS_MODEL_KEY, Gson().toJson(news))
            apply()
        }
    }
}