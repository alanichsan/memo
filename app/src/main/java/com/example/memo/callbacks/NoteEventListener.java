package com.example.memo.callbacks;

import com.example.memo.model.Note;

public interface NoteEventListener {
    /**
     * call wen note clicked
     * @param note: note item
     */
    void onNoteClick(Note note);

    /**
     * call wen long clicked to note
     * @param note : item
     */
    void onNoteLongClick(Note note);

}
