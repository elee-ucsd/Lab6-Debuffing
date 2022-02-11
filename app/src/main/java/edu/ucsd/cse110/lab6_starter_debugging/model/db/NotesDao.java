package edu.ucsd.cse110.lab6_starter_debugging.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface NotesDao {
    @Transaction
    @Query("SELECT * FROM notes where person_id=:personId")
    List<Note> getForPerson(int personId);

    @Query("SELECT * FROM notes WHERE person_id=:id")
    Note get(int id);

    @Query("SELECT COUNT(1) FROM notes WHERE person_id=:id")
    int getSize(int id);

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);
}