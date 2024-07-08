package com.example.note.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


@Dao
interface NoteDao {
    @Upsert
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE importance = :RED_IMPORTANCE")
    fun getContactsByImportanceRed(RED_IMPORTANCE: SortType): Flow<List<Note>>

    @Query("SELECT COUNT(importance) FROM note WHERE importance = :RED_IMPORTANCE")
    fun getCountContactsByImportanceRed(RED_IMPORTANCE: SortType): Flow<Int>

    @Query("SELECT * FROM note WHERE importance = :YELLOW_IMPORTANCE")
    fun getContactsByImportanceYellow(YELLOW_IMPORTANCE: SortType): Flow<List<Note>>

    @Query("SELECT COUNT(importance) FROM note WHERE importance = :YELLOW_IMPORTANCE")
    fun getCountContactsByImportanceYellow(YELLOW_IMPORTANCE: SortType):  Flow<Int>

    @Query("SELECT * FROM note WHERE importance = :GREEN_IMPORTANCE")
    fun getContactsByImportanceGreen(GREEN_IMPORTANCE: SortType): Flow<List<Note>>

    @Query("SELECT COUNT(importance) FROM note WHERE importance = :GREEN_IMPORTANCE")
    fun getCountContactsByImportanceGreen(GREEN_IMPORTANCE: SortType):  Flow<Int>

    @Query("Select * from note where id =:id ")
    fun getAWishesById(id: Int): Flow<List<Note>>
}