package com.example.woof.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class GameEntity(
    @ColumnInfo(name="title")public val title: String,
    @ColumnInfo(name="image_resource_id")public val imageResourceId: Int,
    @ColumnInfo(name="condition")public val condition: Int,
    @ColumnInfo(name="release_year")public val releaseYear: Int,
    @ColumnInfo(name="console")public val console: String,
    @ColumnInfo(name="description")public val description: String,
    @ColumnInfo(name="selected")public val selected: Boolean = false,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}