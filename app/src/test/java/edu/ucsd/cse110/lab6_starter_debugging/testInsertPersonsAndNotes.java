package edu.ucsd.cse110.lab6_starter_debugging;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.List;

import edu.ucsd.cse110.lab6_starter_debugging.model.db.AppDatabase;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.Note;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.Person;

@RunWith(AndroidJUnit4.class)
public class testInsertPersonsAndNotes {
    private AppDatabase db;

    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void use_test_db() {
        Context context = ApplicationProvider.getApplicationContext();
        db = AppDatabase.useTestSingleton(context);
    }

    @Test
    public void test_insert_persons_and_notes() {
        Person newPerson1 = new Person("person1");
        Person newPerson2 = new Person("person2");
        db.personsDao().insert(newPerson1);
        db.personsDao().insert(newPerson2);
        int personId1 = db.personsDao().getId("person1");
        int personId2 = db.personsDao().getId("person2");
        Note newNote1 = new Note(personId1, "note1");
        Note newNote2 = new Note(personId2, "note2");
        db.notesDao().insert(newNote1);
        db.notesDao().insert(newNote2);

        List<Person> allPersons = db.personsDao().getAll();

        assertEquals(2, db.personsDao().getSize());
        assertEquals(1, db.notesDao().getSize(personId1));
        assertEquals(1, db.notesDao().getSize(personId2));
    }
}