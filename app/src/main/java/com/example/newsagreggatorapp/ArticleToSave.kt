package com.example.newsagreggatorapp

/**
 * This class represent object to be save in sqlite database
 *
 * @author Piotr Obara
 * 967793
 */
class ArticleToSave(val id: Int, val title: String, var source: String, var publishedAt: String,
                    var urlToImage: String, var url: String, var description: String,
                    val user: String?) {
}