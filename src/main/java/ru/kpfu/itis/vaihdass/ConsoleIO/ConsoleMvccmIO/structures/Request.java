package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Input;

public class Request {
    private final String data;
    private final Input input;

    public Request(String data, Input input) {
        if (data == null || input == null) throw new IllegalArgumentException("Data or/and input can't be null.");
        this.data = data;
        this.input = input;
    }

    public String getData() {
        return data;
    }

    public Input getInput() {
        return input;
    }
}
