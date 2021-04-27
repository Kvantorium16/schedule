package com.example.schedule

class Model(newImage: Int, newTitle: String, newDescription: String) {
    private var image :Int = newImage
    private var title :String = newTitle
    private var description :String = newDescription

    fun getImage(): Int {
        return image
    }

    fun setImage(newImage: Int) {
        this.image = newImage
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getDesc(): String {
        return description
    }

    fun setDesc(newDescription: String) {
        this.description = newDescription
    }
}