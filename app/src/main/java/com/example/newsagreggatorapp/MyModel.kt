package com.example.newsagreggatorapp

class MyModel {
    var modelName: String? = null
    private var modelImage: Int = 0
    var modelText: String? = null

    fun getName(): String {
        return this.modelName.toString()
    }

    fun setName(name: String) {
        this.modelName = name
    }

    fun getImage(): Int {
        return this.modelImage
    }

    fun setImage(image: Int) {
        this.modelImage = image
    }

    fun getText(): String {
        return this.modelText.toString()
    }

    fun setText(text: String) {
        this.modelText = text
    }

}