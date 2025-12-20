package com.dyusov.news.domain.usecase.articles

import com.dyusov.news.domain.repo.NewsRepository
import com.dyusov.news.domain.repo.SettingsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateSubscribedArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): List<String> {
        val settings = settingsRepository.getSettings().first()
        return newsRepository.updateAllArticles(settings.language)
    }
}