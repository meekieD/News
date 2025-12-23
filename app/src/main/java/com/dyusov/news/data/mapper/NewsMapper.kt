package com.dyusov.news.data.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dyusov.news.R
import com.dyusov.news.data.local.ArticleDbModel
import com.dyusov.news.data.remote.NewsResponseDto
import com.dyusov.news.domain.entity.Article
import com.dyusov.news.domain.entity.Interval
import com.dyusov.news.domain.entity.Language
import java.text.SimpleDateFormat
import java.util.Locale

fun NewsResponseDto.toDbModels(topic: String): List<ArticleDbModel> {
    return articles.map {
        ArticleDbModel(
            title = it.title,
            description = it.description,
            url = it.url,
            imageUrl = it.urlToImage,
            source = it.source.name,
            topic = topic,
            publishedAt = it.publishedAt.toTimestamp()
        )
    }
}

fun List<ArticleDbModel>.toEntities(): List<Article> {
    return map {
        Article(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            source = it.source,
            publishedAt = it.publishedAt,
            url = it.url
        )
    }.distinct() // delete duplicates
}

fun Language.toQueryParam(): String {
    return when (this) {
        Language.ENGLISH -> "en"
        Language.RUSSIAN -> "ru"
        Language.FRENCH -> "fr"
        Language.GERMAN -> "de"
    }
}

@Composable
fun Language.toReadableContent(): String {
    return when (this) {
        Language.ENGLISH -> stringResource(R.string.english)
        Language.RUSSIAN -> stringResource(R.string.russian)
        Language.FRENCH -> stringResource(R.string.french)
        Language.GERMAN -> stringResource(R.string.german)
    }
}

@Composable
fun Interval.toReadableContent(): String {
    return when (this) {
        Interval.MIN_15 -> stringResource(R.string._15_minutes)
        Interval.MIN_30 -> stringResource(R.string._30_minutes)
        Interval.HOUR_1 -> stringResource(R.string._1_hour)
        Interval.HOUR_2 -> stringResource(R.string._2_hours)
        Interval.HOUR_4 -> stringResource(R.string._4_hours)
        Interval.HOUR_8 -> stringResource(R.string._8_hours)
        Interval.HOUR_12 -> stringResource(R.string._12_hours)
        Interval.HOUR_24 -> stringResource(R.string._24_hours)
    }
}

private fun String.toTimestamp(): Long {
    val dateFormatter = SimpleDateFormat(
        /* pattern = */ "yyyy-MM-dd'T'HH:mm:ss'Z'",
        /* locale = */ Locale.getDefault()
    )
    return dateFormatter.parse(this)?.time ?: System.currentTimeMillis()
}