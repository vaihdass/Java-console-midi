package ru.kpfu.itis.vaihdass.Abstractions;

import java.nio.file.Path;

public interface MusicSaver {
    void save(MusicalScore musicalScore, Path path);
}
