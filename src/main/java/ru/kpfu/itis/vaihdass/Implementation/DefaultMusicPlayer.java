package ru.kpfu.itis.vaihdass.Implementation;

import ru.kpfu.itis.vaihdass.Abstractions.MusicPlayer;
import ru.kpfu.itis.vaihdass.Abstractions.MusicalScore;
import ru.kpfu.itis.vaihdass.Abstractions.Note;
import ru.kpfu.itis.vaihdass.Utils.Notes;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class DefaultMusicPlayer implements MusicPlayer {
    @Override
    public void play(MusicalScore musicalScore) {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel midiChannel = synth.getChannels()[0];
            midiChannel.programChange(musicalScore.getInstrument().cod());

            for (final Note note : musicalScore.getNotes()) {
                playNote(midiChannel, note, musicalScore.getTempo());
            }

            synth.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred during playback. Can't play this musical score.");
        }
    }

    private void playNote(MidiChannel midiChannel, Note note, int tempo) throws Exception {
        if (note.getNoteCod() != -1) {
            midiChannel.noteOn(note.getNoteCod(), note.getVolume().cod());
            Thread.sleep(Notes.getMillisecondsDuration(note.getDuration(), tempo));
            midiChannel.noteOff(note.getNoteCod());
        } else {
            Thread.sleep(Notes.getMillisecondsDuration(note.getDuration(), tempo));
        }
    }
}
