package com.example.noteapp2.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.noteapp2.Models.Note;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(Note note);

    @Query("SELECT * from notes ORDER BY id DESC")
    List<Note> getAll();


    @Query("UPDATE notes SET title = :title, description = :description WHERE ID = :id")
    void update(int id, String title, String description);

    @Delete
    void delete(Note note);

}
