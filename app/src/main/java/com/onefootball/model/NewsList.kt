package com.onefootball.model

import com.google.gson.annotations.SerializedName

data class NewsList(
    @SerializedName("news") val news: List<News>
)