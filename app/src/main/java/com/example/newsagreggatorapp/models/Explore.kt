package com.example.newsagreggatorapp.models

/**
 * This class represent Explore model
 *
 * @author Piotr Obara
 */
class Explore {
    var modelImage: Int = 0
    var modelName: String = ""

    fun getImage(): Int {
        return this.modelImage
    }

    fun setImage(image: Int) {
        this.modelImage = image
    }

}