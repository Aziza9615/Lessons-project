package com.example.firstapp.data

import androidx.room.TypeConverter
import com.example.firstapp.model.Comment
import com.example.firstapp.model.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PublicationTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun imagesToString(data: MutableList<Images>?): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toImagesFromString(json: String): MutableList<Images>? {
        val listType = object: TypeToken<MutableList<Images>?>() {
        }.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun commentsToString(data: MutableList<Comment>?): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toCommentsFromString(json: String): MutableList<Comment>? {
        val listType = object: TypeToken<MutableList<Comment>?>() {
        }.type
        return gson.fromJson(json, listType)
    }

}