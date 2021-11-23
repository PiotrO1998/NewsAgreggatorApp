package com.example.newsagreggatorapp.models

/**
 * This class represent News model
 *
 * @author Piotr Obara
 */
data class News (

    var status: String,
    var totalResults: Int,
    var articles: ArrayList<Article>,
    var sources: ArrayList<Source>

)









