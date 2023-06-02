package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.CommandManager;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Input;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Output;

import java.util.List;

public class Properties {
    private Input input;
    private Output output;
    private String command;
    private List<String> args;

    private CommandManager commandManager;

    public Properties(Input input, Output output) {
        if (input == null || output == null) throw new IllegalArgumentException("Input or/and Output can't be null");
        this.input = input;
        this.output = output;
    }

    public Input getInput() {
        return input;
    }

    public Output getOutput() {
        return output;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
