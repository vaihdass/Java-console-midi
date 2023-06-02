package ru.kpfu.itis.vaihdass.Utils;

import ru.kpfu.itis.vaihdass.Abstractions.Note;
import ru.kpfu.itis.vaihdass.Implementation.DefaultNote;
import ru.kpfu.itis.vaihdass.Implementation.Instruments;
import ru.kpfu.itis.vaihdass.Implementation.NoteNotations;
import ru.kpfu.itis.vaihdass.Implementation.Volumes;

public abstract class Notes {
    public static int getMillisecondsDuration(int duration, int tempo) {
        if (duration < 1 || tempo < 1 || tempo > 750) return 0;
        return (60000 / 8 / tempo) * duration;
    }

    public static Note createDefaultNodeOrNull(Instruments instrument, String name, String duration, String volume) {
        if (name == null || duration == null || volume == null) return null;

        name = name.trim().toUpperCase();
        duration = duration.trim();
        volume = volume.trim().toUpperCase();

        int durationResult;
        try {
            durationResult = Integer.parseInt(duration);
        } catch (NumberFormatException e) {
            return null;
        }

        Volumes volumeResult;
        try {
            volumeResult = Volumes.valueOf(volume);
        } catch (IllegalArgumentException e) {
            return null;
        }

        return new DefaultNote(instrument, getNoteCodByName(name) == -1 ? "Pause" : name, durationResult, volumeResult);
    }

    public static int getNoteCodByName(String name) {
        if (name == null) return -1; // It's pause by default if incorrect name
        name = name.trim().replace("#", "1");

        if (name.length() < 2 || name.equalsIgnoreCase("-1")) return -1;

        if (name.charAt(0) == '-' && name.length() < 3) return -1;

        int octaveCod;
        String codStringify = String.valueOf(name.charAt(0));
        if (name.charAt(0) == '-') {
            codStringify += name.charAt(1);
        }
        try {
            octaveCod = Integer.parseInt(codStringify);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (octaveCod < -2 || octaveCod > 5) return -1;

        octaveCod += 3;

        int noteCodStart = 1;
        if (name.charAt(0) == '-') noteCodStart++;
        String noteStringify = name.substring(noteCodStart);
        if (noteStringify.length() > 2) return -1;

        NoteNotations noteNotation;
        try {
            noteNotation = NoteNotations.valueOf(noteStringify);
        } catch (IllegalArgumentException e) {
            return -1;
        }

        return noteNotation.getCod() + 12 * (octaveCod + 1);
    }
}
