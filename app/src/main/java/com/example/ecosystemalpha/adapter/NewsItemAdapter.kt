package com.example.ecosystemalpha.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ecosystemalpha.model.NewsModel
import com.example.ecosystemalpha.databinding.NewsItemBinding


class NewsItemAdapter(
    private val context: Context,
    private var newsItemList: List<NewsModel>
) : RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsModel) = with(binding) {
            textView.text = item.headline
            imageView.load(item.image)
            cardView.setOnClickListener {
                startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(item.url)), null)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = NewsViewHolder(
        NewsItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsItemList[position])
    }

    override fun getItemCount(): Int = newsItemList.size


    fun updateItemList(newNewsItemList: List<NewsModel>) {
        newsItemList = newNewsItemList
        notifyDataSetChanged()
    }
}