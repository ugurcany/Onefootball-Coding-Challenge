package com.onefootball.ui.mynews

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.onefootball.R
import com.onefootball.databinding.ViewNewsItemBinding
import com.onefootball.model.News

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private val newsItems = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = DataBindingUtil.inflate<ViewNewsItemBinding>(
            LayoutInflater.from(parent.context), R.layout.view_news_item, parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = newsItems.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsItems[position]

        holder.binding.apply {
            newsTitle.text = news.title
            newsView.load(url = news.imageURL)
            resourceIcon.load(url = news.resourceURL)
            resourceName.text = news.resourceName

            root.setOnClickListener {
                it.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(news.newsLink))
                )
            }
        }
    }

    fun setNewsItems(newListOfNewsItems: List<News>) {
        newsItems.clear()
        newsItems.addAll(newListOfNewsItems)
        notifyDataSetChanged()
    }

}

class NewsViewHolder(var binding: ViewNewsItemBinding) : RecyclerView.ViewHolder(binding.root)
