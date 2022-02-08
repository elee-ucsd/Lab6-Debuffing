package edu.ucsd.cse110.lab6_starter_debugging;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ucsd.cse110.lab6_starter_debugging.model.db.AppDatabase;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.Note;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.Person;

public class PersonDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private Person person;

    private RecyclerView notesRecyclerView;
    private RecyclerView.LayoutManager notesLayoutManager;
    private NotesViewAdapter notesViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        Intent intent = getIntent();
        int personId = intent.getIntExtra("person_id", 0);

        db = AppDatabase.singleton(this);
        person = db.personsDao().get(personId);
        List<Note> notes = db.notesDao().getForPerson(personId);

        // Set the title with the person.
        setTitle(person.getName());

        // Set up the recycler view to show our database contents.
        notesRecyclerView = findViewById(R.id.notes_view);
        notesLayoutManager = new LinearLayoutManager(this);
        notesRecyclerView.setLayoutManager(notesLayoutManager);

        notesViewAdapter = new NotesViewAdapter(notes, (note) -> {
            db.notesDao().delete(note);
        });
        notesRecyclerView.setAdapter(notesViewAdapter);

    }

    public void onAddNoteClicked(View view) {
        int personId = person.getPersonId();
        TextView newNoteTextView = findViewById(R.id.new_note_textview);
        String newNoteText = newNoteTextView.getText().toString();

        Note newNote = new Note(personId, newNoteText);
        db.notesDao().insert(newNote);

        notesViewAdapter.addNote(newNote);
    }

    public void onGoBackClicked(View view) {
        finish();
    }
}

