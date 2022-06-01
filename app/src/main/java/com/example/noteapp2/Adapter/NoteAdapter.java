package com.example.noteapp2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp2.Models.Note;
import com.example.noteapp2.NoteDescriptionActivity;
import com.example.noteapp2.OnNoteClickListener;
import com.example.noteapp2.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    Context context;
    List<Note> noteList;
    OnNoteClickListener listener;

    public NoteAdapter(Context context, List<Note> noteList, OnNoteClickListener listener) {
        this.context = context;
        this.noteList = noteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tv_itemTitle.setText(noteList.get(position).getTitle());
        holder.tv_itemDescription.setText(noteList.get(position).getDescription());
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNoteClick(noteList.get(holder.getAdapterPosition()));
            }
        });
        holder.itemCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onNoteLongClick(noteList.get(holder.getAdapterPosition()), holder.itemCard);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tv_itemTitle, tv_itemDescription;
        CardView itemCard;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_itemTitle = itemView.findViewById(R.id.tv_itemTitle);
            tv_itemDescription = itemView.findViewById(R.id.tv_itemDescription);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
