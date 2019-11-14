package com.onefootball.repository.news

import android.content.Context
import com.google.gson.Gson
import com.onefootball.model.News
import com.onefootball.model.NewsList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private var appContext: Context
) : INewsRepository {

    override fun getNews(): Observable<List<News>> {
        return Observable.create<List<News>> {
            var inputStream: InputStream? = null
            try {
                inputStream = appContext.assets.open("news.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)

                inputStream.read(buffer)
                val jsonString = buffer.toString(Charset.defaultCharset())

                //PARSE JSON CONTENT INTO NEWSLIST OBJECT
                val newsList = Gson().fromJson<NewsList>(
                    jsonString,
                    NewsList::class.java
                )
                it.onNext(newsList.news)
                it.onComplete()
            } catch (e: Exception) {
                it.tryOnError(e)
            } finally {
                inputStream?.close()
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}