package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.MusicSaver;
import ru.kpfu.itis.vaihdass.Abstractions.MusicalScore;
import ru.kpfu.itis.vaihdass.Abstractions.Note;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class DefaultMusicSaver implements MusicSaver {
    private final String lineBreak;
    private final String propertiesBreak;

    public DefaultMusicSaver() {
        this("\n", ",");
    }

    public DefaultMusicSaver(String lineBreak, String propertiesBreak) {
        this.lineBreak = lineBreak;
        this.propertiesBreak = propertiesBreak;
    }

    @Override
    public void save(MusicalScore musicalScore, Path path) {
        if (musicalScore == null || musicalScore.getInstrument() == null || musicalScore.getTempo() == 0) return;

        try {
            if (Files.notExists(path)) Files.createFile(path);
            try (PrintWriter printWriter = new PrintWriter(Files.newOutputStream(path))) {
                printWriter.print(musicalScore.getTempo() + propertiesBreak + musicalScore.getInstrument().getName() + lineBreak);
                for (final Note note : musicalScore.getNotes()) {
                    printWriter.print(note.getNote() + propertiesBreak + note.getDuration() + propertiesBreak + note.getVolume().getName() + lineBreak);
                }
            }
        } catch (IOException ignored) {}
    }
}
