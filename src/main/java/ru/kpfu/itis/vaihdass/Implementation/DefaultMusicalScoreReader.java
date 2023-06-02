package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.MusicalScore;
import ru.kpfu.itis.vaihdass.Abstractions.MusicalScoreReader;
import ru.kpfu.itis.vaihdass.Utils.Notes;

public class DefaultMusicalScoreReader implements MusicalScoreReader {
    private final String lineBreak;
    private final String propertiesBreak;

    public DefaultMusicalScoreReader() {
        this("\n", ",");
    }

    public DefaultMusicalScoreReader(String lineBreak, String propertiesBreak) {
        this.lineBreak = lineBreak;
        this.propertiesBreak = propertiesBreak;
    }

    @Override
    public MusicalScore readMusicalScore(String musicalScoreStringify) {
        if (musicalScoreStringify == null || musicalScoreStringify.length() == 0) return null;
        String[] lines = musicalScoreStringify.split(lineBreak);
        if (lines.length == 0) return null;

        String[] headerProperties = lines[0].split(propertiesBreak);
        if (headerProperties.length < 2) return null;

        int tempo;
        try {
            tempo = Integer.parseInt(headerProperties[0].trim());
        } catch (NumberFormatException e) {
            return null;
        }
        if (tempo < 1 || tempo > 750) return null;

        Instruments instrument;
        try {
            instrument = Instruments.valueOf(headerProperties[1].trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return null;
        }

        MusicalScore musicalScore = new DefaultMusicalScore(tempo, instrument);
        if (lines.length > 1) {
            for (int i = 1; i < lines.length; i++) {
                String[] noteProperties = lines[i].split(propertiesBreak);
                if (noteProperties.length < 3) continue;
                musicalScore.addNote(Notes.createDefaultNodeOrNull(instrument, noteProperties[0],
                        noteProperties[1], noteProperties[2]));
            }
        }

        return musicalScore;
    }
}
