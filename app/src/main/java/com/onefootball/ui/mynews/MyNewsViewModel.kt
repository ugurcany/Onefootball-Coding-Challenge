package com.onefootball.ui.mynews

import androidx.lifecycle.MutableLiveData
import com.onefootball.model.News
import com.onefootball.model.RequestResult
import com.onefootball.repository.news.INewsRepository
import com.onefootball.ui.base.BaseViewModel
import javax.inject.Inject

class MyNewsViewModel @Inject internal constructor(
    private val newsRepository: INewsRepository
) : BaseViewModel() {

    val newsResult = MutableLiveData<RequestResult<List<News>>>()

    fun getNews() {
        newsResult.value = RequestResult.loading()
        willDispose(newsRepository.getNews()
            .subscribe(
                {
                    newsResult.value = RequestResult.success(it)
                },
                {
                    newsResult.value = RequestResult.error(it)
                }
            ), "GET_NEWS")
    }
}