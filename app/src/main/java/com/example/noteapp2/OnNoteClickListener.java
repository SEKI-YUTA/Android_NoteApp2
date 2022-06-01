package com.example.noteapp2;

import androidx.cardview.widget.CardView;

import com.example.noteapp2.Models.Note;

public interface OnNoteClickListener {
    void onNoteClick(Note note);
    void onNoteLongClick(Note note, CardView cardView);
}
