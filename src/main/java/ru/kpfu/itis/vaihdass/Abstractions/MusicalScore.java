package ru.kpfu.itis.vaihdass.Abstractions;

import ru.kpfu.itis.vaihdass.Implementation.Instruments;

import java.util.List;

public interface MusicalScore {
    int getTempo();
    Instruments getInstrument();
    List<Note> getNotes();
    void addNote(Note note);
}
