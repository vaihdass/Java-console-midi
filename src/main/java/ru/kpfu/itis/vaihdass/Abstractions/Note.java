package ru.kpfu.itis.vaihdass.Abstractions;

import ru.kpfu.itis.vaihdass.Implementation.Instruments;
import ru.kpfu.itis.vaihdass.Implementation.Volumes;

public interface Note {
    Instruments getInstrument();
    String getNote();
    int getNoteCod();
    int getDuration();
    Volumes getVolume();
}
