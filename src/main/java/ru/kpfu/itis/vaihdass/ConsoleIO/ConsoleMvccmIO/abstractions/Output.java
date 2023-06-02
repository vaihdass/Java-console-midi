package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;

public interface Output {
    void updateView(Response response) throws IllegalArgumentException;
}
