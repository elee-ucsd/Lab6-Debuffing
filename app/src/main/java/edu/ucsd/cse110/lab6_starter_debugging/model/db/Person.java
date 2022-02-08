package edu.ucsd.cse110.lab6_starter_debugging.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "persons")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    private int personId = 0;

    @ColumnInfo(name = "name")
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

