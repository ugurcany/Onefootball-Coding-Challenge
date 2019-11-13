package com.onefootball.repository.news

import com.onefootball.model.News
import io.reactivex.Observable

interface INewsRepository {

    fun getNews(): Observable<List<News>>

}