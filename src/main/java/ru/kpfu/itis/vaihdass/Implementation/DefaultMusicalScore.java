package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.MusicalScore;
import ru.kpfu.itis.vaihdass.Abstractions.Note;

import java.util.LinkedList;
import java.util.List;

public class DefaultMusicalScore implements MusicalScore {
    private final int tempo;
    private final Instruments instrument;
    private final List<Note> notes;

    public DefaultMusicalScore(int tempo, Instruments instrument) {
        this(tempo, instrument, null);
    }

    public DefaultMusicalScore(int tempo, Instruments instrument, List<Note> notes) {
        this.tempo = tempo;
        this.instrument = instrument;
        this.notes = new LinkedList<>();
        if (notes != null) {
            for (final Note note : notes) addNote(note);
        }
    }

    @Override
    public void addNote(Note note) {
        if (note != null) notes.add(note);
    }

    @Override
    public int getTempo() {
        return tempo;
    }

    @Override
    public Instruments getInstrument() {
        return instrument;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }
}
