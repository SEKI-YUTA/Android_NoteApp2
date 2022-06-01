package com.example.noteapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.noteapp2.Database.RoomDB;
import com.example.noteapp2.Models.Note;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    EditText titleInput, descriptionInput;
    MaterialButton btn_save;
    RoomDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        btn_save = findViewById(R.id.btn_save);



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1.validation
                // 2. add data to db
                Date now = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String createdAt = formatter.format(now);
                String newNoteTitle = titleInput.getText().toString();
                String newNoteDescription = descriptionInput.getText().toString();
                Log.d("MyLog", newNoteTitle);
                Log.d("MyLog", newNoteDescription);
                Note newNote = new Note();
                newNote.setTitle(newNoteTitle);
                newNote.setDescription(newNoteDescription);
                newNote.setCreatedTime(createdAt);

                Intent intent = new Intent();
                intent.putExtra("note", newNote);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }
}