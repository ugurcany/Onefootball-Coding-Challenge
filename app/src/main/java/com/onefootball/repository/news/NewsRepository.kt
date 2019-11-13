package com.onefootball.repository.news

import android.content.Context
import com.onefootball.model.News
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private var appContext: Context
) : INewsRepository {

    override fun getNews(): Observable<List<News>> {
        return Observable.create {  }
    }

}