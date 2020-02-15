package com.example.memo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.memo.model.Note;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note Date);
@Delete
    void deleteNote(Note Date);
@Update
    void updateNote(Note Date);
@Query("SELECT * FROM notes")
    List<Note> getNotes();
@Query("SELECT * FROM notes WHERE id = :noteId")
    Note getNoteById(int noteId);
@Query("DELETE FROM notes WHERE id = :noteId")
    void deleteNoteById(int noteId);


}
