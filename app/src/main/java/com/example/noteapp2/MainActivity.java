package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.noteapp2.Adapter.NoteAdapter;
import com.example.noteapp2.Database.RoomDB;
import com.example.noteapp2.Models.Note;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnNoteClickListener, PopupMenu.OnMenuItemClickListener {
    FloatingActionButton fab_addNote;
    RecyclerView recyclerView;
    RoomDB database;
    NoteAdapter noteAdapter;
    Note selectedNote;
    List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_addNote = findViewById(R.id.fab_addNote);
        recyclerView = findViewById(R.id.recyclerview);
        database = RoomDB.getInstance(this);
        noteList = database.mainDAO().getAll();

        updateRecycler(noteList);




        fab_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent,101);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101) {
            if(resultCode == Activity.RESULT_OK) {
                Note newNote = (Note) data.getSerializableExtra("note");
                database.mainDAO().insert(newNote);
                noteList.clear();
                noteList.addAll(database.mainDAO().getAll());
                noteAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Note> noteList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        noteAdapter = new NoteAdapter(MainActivity.this, noteList, this);
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(MainActivity.this, NoteDescriptionActivity.class);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    @Override
    public void onNoteLongClick(Note note, CardView cardView) {
        try {
            selectedNote = new Note();
            selectedNote = note;
            showPopup(cardView);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "failed delete the note", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_delete:
                database.mainDAO().delete(selectedNote);
                Toast.makeText(MainActivity.this, "deleted the note.", Toast.LENGTH_SHORT).show();
                noteList.remove(selectedNote);
                noteAdapter.notifyDataSetChanged();
                return true;
            default:
                return false;
        }
    }
}