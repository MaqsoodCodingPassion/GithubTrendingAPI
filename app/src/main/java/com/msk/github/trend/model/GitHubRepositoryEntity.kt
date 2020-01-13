package com.msk.github.trend.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github")
class GitHubRepositoryEntity {

    @SerializedName("id")
    var id: Int = 1

    @PrimaryKey
    @NonNull
    @SerializedName("name")
    var name: String? = null

    @SerializedName("forks")
    var forks: Int = 0

    @SerializedName("author")
    var author: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("language")
    var language: String? = null

    @SerializedName("avatar")
    var avatar: String? = null

    @SerializedName("languageColor")
    var languageColor: String? = null

    @SerializedName("stars")
    var stars: Int = 0

    @SerializedName("url")
    var url: String? = null

    @SerializedName("currentPeriodStars")
    var currentPeriodStars: Int = 0

    @SerializedName("time")
    var time: Long = 0
}

