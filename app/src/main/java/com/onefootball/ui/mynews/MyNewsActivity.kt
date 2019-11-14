package com.onefootball.ui.mynews

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.onefootball.R
import com.onefootball.databinding.ActivityMyNewsBinding
import com.onefootball.ui.base.BaseActivity

class MyNewsActivity : BaseActivity<ActivityMyNewsBinding, MyNewsViewModel>() {

    private val myAdapter: NewsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //INIT RECYCLER VIEW & ADAPTER & LAYOUT MANAGER BASED ON ORIENTATION
        with(binding.newsRecyclerView) {
            adapter = myAdapter
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                    LinearLayoutManager(this@MyNewsActivity)
                else
                    GridLayoutManager(this@MyNewsActivity, 2)
        }

        //OBSERVE NEWS RESULT
        observe(viewModel.newsResult, Observer {
            binding.newsRecyclerView.isVisible = false
            binding.progressBar.isVisible = false

            when {
                it.isLoading() -> {
                    binding.progressBar.isVisible = true
                }
                it.isSuccessful() -> {
                    binding.newsRecyclerView.isVisible = true
                    myAdapter.setNewsItems(it?.data ?: emptyList())
                }
                it.isError() -> {
                    Toast.makeText(this, R.string.err_text, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //FETCH NEWS EVERYTIME ONRESUME IS CALLED
        viewModel.getNews()
    }

    override fun layoutRes(): Int {
        return R.layout.activity_my_news
    }

    override fun viewModelClass(): Class<MyNewsViewModel> {
        return MyNewsViewModel::class.java
    }
}
