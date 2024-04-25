package com.example.lotusaddictionapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @ColumnInfo(name="first_name")public val firstName: String?,
    @ColumnInfo(name="last_name")public val lastName: String?,
    @ColumnInfo(name="email")public val email: String?,
    @ColumnInfo(name="password_hash")public val passHash: String?,
    @ColumnInfo(name="created_date")public val createdDate: Date,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}