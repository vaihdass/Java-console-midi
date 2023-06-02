package ru.kpfu.itis.vaihdass.ConsoleIO.CustomProperties;

import ru.kpfu.itis.vaihdass.Abstractions.MusicSaver;
import ru.kpfu.itis.vaihdass.Abstractions.MusicalScoreReader;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;

public class SaverProperties implements ExpandableProperties {
    private final MusicalScoreReader musicalScoreReader;
    private final MusicSaver musicSaver;

    public SaverProperties(MusicalScoreReader musicalScoreReader, MusicSaver musicSaver) {
        this.musicalScoreReader = musicalScoreReader;
        this.musicSaver = musicSaver;
    }

    public MusicalScoreReader getMusicalScoreReader() {
        return musicalScoreReader;
    }

    public MusicSaver getMusicSaver() {
        return musicSaver;
    }
}
