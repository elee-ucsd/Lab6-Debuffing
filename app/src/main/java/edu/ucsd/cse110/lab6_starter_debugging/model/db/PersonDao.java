package edu.ucsd.cse110.lab6_starter_debugging.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM persons")
    List<Person> getAll();

    @Query("SELECT * FROM persons WHERE person_id=:id")
    Person get(int id);

    @Query("SELECT * FROM persons WHERE name=:name")
    int getId(String name);

    @Query("SELECT COUNT(*) FROM persons")
    int getSize();

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);
}
