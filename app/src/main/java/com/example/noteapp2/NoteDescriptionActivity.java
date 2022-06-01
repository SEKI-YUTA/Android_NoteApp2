package com.example.noteapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.noteapp2.Models.Note;

public class NoteDescriptionActivity extends AppCompatActivity {
    TextView tv_detailTitle, tv_detailDescription, tv_detailCreatedAt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description);
        try {
            Note note = (Note) getIntent().getSerializableExtra("note");
                tv_detailTitle.setText(note.getTitle());
                tv_detailDescription.setText(note.getDescription());
                tv_detailCreatedAt.setText(note.getCreatedTime());
        } catch(Exception e) {
            e.printStackTrace();
            Log.d("MyLog", "正常にデータを取得出来ませんでした。");
        }
    }
}