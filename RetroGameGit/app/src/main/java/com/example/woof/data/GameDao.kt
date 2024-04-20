package com.example.woof.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg games: GameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: GameEntity)

    @Delete
    fun delete(game: GameEntity)

    @Query("SELECT * FROM gameentity WHERE id = :id")
    fun getGameByID(id: Int) : GameEntity

    @Query("SELECT * FROM gameentity WHERE selected = :selected")
    fun getSelectedGame(selected: Boolean = true) : GameEntity

    @Query("SELECT * FROM gameentity WHERE title = :title")
    fun getGameByTitle(title: String) : GameEntity

    @Query("SELECT * FROM gameentity")
    fun getAll(): LiveData<List<GameEntity>>

    @Query("UPDATE gameentity SET selected = :selected WHERE id = :id")
    fun updateGameSelection(id: Int, selected: Boolean)
}