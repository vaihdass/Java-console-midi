package ru.kpfu.itis.vaihdass.ConsoleIO.CustomProperties;

import ru.kpfu.itis.vaihdass.Abstractions.MusicPlayer;
import ru.kpfu.itis.vaihdass.Abstractions.MusicalScoreReader;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;

public class PlayerProperties implements ExpandableProperties {
    private final MusicalScoreReader musicalScoreReader;
    private final MusicPlayer musicPlayer;

    public PlayerProperties(MusicalScoreReader musicalScoreReader, MusicPlayer musicPlayer) {
        this.musicalScoreReader = musicalScoreReader;
        this.musicPlayer = musicPlayer;
    }

    public MusicalScoreReader getMusicalScoreReader() {
        return musicalScoreReader;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }
}
