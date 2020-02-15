package com.example.memo.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memo.R;
import com.example.memo.callbacks.NoteEventListener;
import com.example.memo.model.Note;
import com.example.memo.utils.NoteUtils;

import java.util.ArrayList;
import java.util.Date;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder>{
    private Context context;
    private ArrayList<Note> notes;
    private NoteEventListener listener;

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.note_layout, parent,false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        final Note note = getNote(position);
        if (note!=null){
            holder.noteText.setText(note.getNoteText());
            holder.noteDate.setText(NoteUtils.dateFromLong(note.getNoteDate()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNoteClick(note);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onNoteLongClick(note);
                    return false;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private Note getNote(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{

        TextView noteText,noteDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            noteDate=itemView.findViewById(R.id.note_date);
            noteText=itemView.findViewById(R.id.note_text);
            //20:15
        }
    }

    public void setListener(NoteEventListener listener) {
        this.listener = listener;
    }
}
