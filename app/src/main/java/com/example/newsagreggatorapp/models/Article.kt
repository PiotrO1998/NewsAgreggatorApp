package com.example.newsagreggatorapp.models

import java.io.Serializable

/**
 * This class represent Article model
 *
 * @author Piotr Obara
 */
data class Article (

    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String

) : Serializable



    /*
    var source: Source? = null
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: String? = null
    var content: String? = null
    var category: String? = null

}

    fun setSourcee(source: Source) {
        this.source = source
    }

    fun getSourcee(): Source? {
        return this.source
    }

    fun setAuthorr(author: String) {
        this.author = author
    }

    fun getAuthorr(): String {
        return this.author.toString()
    }

    fun setTitlee(title: String){
        this.title = title
    }

    fun getTitlee(): String {
        return this.title.toString()
    }

    fun setDescriptionn(description: String) {
        this.description = description
    }

    fun getDescriptionn(): String {
        return this.description.toString()
    }

    fun setUrll(url: String){
        this.url = url
    }

    fun getUrll(): String {
        return this.url.toString()
    }

    fun setUrlToImagee(url: String) {
        this.urlToImage = urlToImage
    }

    fun getUrlToImagee(): String {
        return this.urlToImage.toString()
    }

    fun setPublishedAtt(publisedAt: String) {
        this.publishedAt = publishedAt
    }

    fun getPublishedAtt(): String {
        return this.publishedAt.toString()
    }
*/
