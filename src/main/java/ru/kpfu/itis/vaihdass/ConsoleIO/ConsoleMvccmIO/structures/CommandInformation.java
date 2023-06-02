package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.*;

public class CommandInformation {
    private final String commandName;
    private final String description;

    public CommandInformation(String commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }

    @Override
    public String toString() {
        return yellow((commandName != null ? commandName : "Unknown command").toUpperCase())
                + purple(" -> ")
                + cyan(description != null ? description : "No description");
    }
}