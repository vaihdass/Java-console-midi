package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.Note;
import ru.kpfu.itis.vaihdass.Utils.Notes;

public class DefaultNote implements Note {
    private String name;
    private Instruments instrument;
    private int duration;
    private Volumes volume;

    public DefaultNote(Instruments instrument, String name, int duration, Volumes volume) {
        this.instrument = instrument;
        this.name = name;
        if (duration < 1) duration = 1;
        this.duration = duration;
        this.volume = volume;
    }

    @Override
    public Instruments getInstrument() {
        return instrument;
    }

    @Override
    public String getNote() {
        return name;
    }

    @Override
    public int getNoteCod() {
        return Notes.getNoteCodByName(name);
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public Volumes getVolume() {
        return volume;
    }
}
