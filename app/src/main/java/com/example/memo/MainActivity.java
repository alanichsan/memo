package com.example.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.memo.adapters.NotesAdapter;
import com.example.memo.callbacks.NoteEventListener;
import com.example.memo.db.NotesDB;
import com.example.memo.db.NotesDao;
import com.example.memo.model.Note;
import com.example.memo.utils.NoteUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

import static com.example.memo.EditeNoteActivity.NOTE_EXTRA_Key;

public class MainActivity extends AppCompatActivity implements NoteEventListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.note_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2/11/2020 add new note
                onAddnewNote();
            }
        });

        dao = NotesDB.getInstance(this).notesDao();
    }

    private void loadNotes() {
        this.notes = new ArrayList<>();
        List<Note> list = dao.getNotes();
        this.notes.addAll(list);
        this.adapter = new NotesAdapter(this, this.notes);
        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);
    }

    private void onAddnewNote() {
        // TODO: 2/12/2020 start EditNoteActivity
        startActivity(new Intent(this, EditeNoteActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note) {
        // TODO: 2/13/2020 note clicked : edit note
        Intent edit = new Intent(this, EditeNoteActivity.class);
        edit.putExtra(NOTE_EXTRA_Key, note.getId());
        startActivity(edit);
    }

    @Override
    public void onNoteLongClick(Note note) {
        // TODO: 2/13/2020 note long clicked : delete,share ..

//        new AlertDialog.Builder(this)
//                .setTitle(R.string.app_name)
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                })
//                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // TODO: 2/13/2020 delete note from database adn refresh
//
//                        dao.deleteNote(note);
//                        loadNotes();
//                    }
//                })
//                .setNegativeButton("Share", new DialogInterface.OnClickListener() {
//                    String name = "Alan";
//
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent share = new Intent(Intent.ACTION_SEND);
//                        String text = note.getNoteText() + " By :" + name;
//                        share.setType("text/plain");
//                        share.putExtra(Intent.EXTRA_TEXT, text);
//
//                        startActivity(share);
//
//                    }
//                })
//                .create()
//                .show();
    }
}
//+ "\n Create on:" +NoteUtils.dateFromLong(note.getNoteDate())
