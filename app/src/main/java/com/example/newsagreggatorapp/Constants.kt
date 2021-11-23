package com.example.newsagreggatorapp

/**
 * This class represnet constants variables
 *
 * @author Piotr Obara
 * 967793
 */
class Constants {
    companion object {

        const val APIKEY = "b5135fc0dd774940b7410a9ba293f4f8"
        const val BASEURL = "https://newsapi.org"

        const val DATABASE_VERSION = 5
        const val DATABASE_NAME = "userPreference"
        const val TABLE_PREFERENCES = "preferences"
        const val COLUMN_ID = "_id"
        const val COLUMN_PREFERENCE_TITLE = "preferenceName"
        const val COLUMN_USER = "user"

        const val DATABASE_VERSION_ARTICLE = 5
        const val DATABASE_NAME_ARTICLE = "userArticles"
        const val TABLE_ARTICLES = "articles"
        const val COLUMN_ID_ARTICLE = "_id"
        const val COLUMN_TITLE_ARTICLE = "articleTitle"
        const val COLUMN_SOURCE_ARTICLE = "articleSource"
        const val COLUMN_PUBLISHEDAT_ARTICLE = "articlePublishedAt"
        const val COLUMN_URLTOIMAGE_ARTICLE = "articleUrlToImage"
        const val COLUMN_URL_ARTICLE = "articleUrl"
        const val COLUMN_DESCRIPTION_ARTICLE = "description"
        const val COLUMN_USER_ARTICLE = "user"

        const val DATABASE_NAME_LIKE = "userLike"
        const val TABLE_LIKES = "likes"
        const val COLUMN_ID_LIKE = "_id"
        const val COLUMN_LIKE_NAME = "likeName"
        const val COLUMN_USER_LIKE = "user"
        const val COLUMN_NUMBER_OF_LIKE = "numberOfLike"


    }
}