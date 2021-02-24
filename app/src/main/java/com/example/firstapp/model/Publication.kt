package com.example.firstapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.firstapp.data.PublicationTypeConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "publications")
@TypeConverters(PublicationTypeConverter::class)
data class Publication(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    @SerializedName("image") var icon: String? = null,
    var name: String? = null,
    var phoneNumber: String? = null,
    var age: Int? = null,
    var countOfFavorite: Int = 0,
    var countOfComment: Int = 0,
    var images: MutableList<Images>? = null,
    val randomImage: String? = null,
    val comments: MutableList<Comment>? = null,
    var isComment: Boolean = false,
    var isFavorite: Boolean = true
): Serializable

data class Images(
    var url: String? = null) {
}