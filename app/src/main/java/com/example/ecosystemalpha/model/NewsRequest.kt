package com.example.ecosystemalpha.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

private const val URL = "https://api.nytimes.com/svc/topstories/v2/world.json?api-key="
private const val API_KEY = "rWcNeriGfSd5ZW65cQjaLHqzWI2SY4sQ"

class NewsRequest : NewsApi {
    override suspend fun getData(): List<NewsModel> {
        val json = fetchData(URL + API_KEY)
        return getNewsModelList(json)
    }

    private suspend fun fetchData(url: String) =
        withContext(Dispatchers.IO) { JSONObject(URL(url).readText()) }

    private fun getNewsModelList(json: JSONObject): List<NewsModel> {
        val list = mutableListOf<NewsModel>()
        for (i in 1..<json.getString("num_results").toInt()) {
            list.add(
                NewsModel(
                    json.getJSONArray("results").getJSONObject(i).getString("title"),
                    json.getJSONArray("results").getJSONObject(i).getJSONArray("multimedia")
                        .getJSONObject(0).getString("url"),
                    json.getJSONArray("results").getJSONObject(i).getString("url")
                )
            )
        }
        return list
    }
}