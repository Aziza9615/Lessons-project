package com.example.firstapp.data

import androidx.room.*
import com.example.firstapp.model.Publication

@Dao
interface InstagramDao {
    @Insert(onConflict = OnConflictStrategy. IGNORE)
    fun InsertPublications(data: List<Publication>?)

    @Query("SELECT * FROM publications")
    fun fetchPublications(): List<Publication>

    @Query("SELECT * FROM publications WHERE isFavorite == 1")
    fun fetchFavoritePublications(): List<Publication>

    @Update
    fun updateChangeFavoriteState(data: Publication)
}

//    @Insert - Post - Добавление
//    @Update - Put - Изменение
//    @Query - Произвольный запрос
//    @Delete - Delete - Удаление