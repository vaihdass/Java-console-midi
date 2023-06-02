package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.*;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.implementation.*;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.CommandMapEntry;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.CommandMapEntriesBuilder;

import java.io.InputStream;
import java.util.Map;

public class ConsoleIOFacade implements Runnable {
    private Output output;
    private CommandManager commandManager;
    private Model model;
    private Controller controller;
    private Input input;

    public ConsoleIOFacade(String greeting, DefaultViewGetter defaultViewGetterCallback, InputStream commandsInputStream) {
        this(greeting, defaultViewGetterCallback, commandsInputStream, null);
    }

    public ConsoleIOFacade(String greeting, DefaultViewGetter defaultViewGetterCallback,
                           InputStream commandsInputStream, Map<String, CommandMapEntry> customCommands) {
        output = new ConsoleOutput();

        commandManager = new SimpleCommandManager();
        CommandMapEntriesBuilder.fillCommandManagerWithBasicCommands(commandManager);

        model = new SimpleModel(commandManager, defaultViewGetterCallback);

        controller = new SimpleController(output, model, greeting);

        input = new ConsoleInput(controller, commandsInputStream);

        if (customCommands != null) {
            for (final Map.Entry<String, CommandMapEntry> command : customCommands.entrySet()) {
                try {
                    addNewCustomCommand(command.getKey(), command.getValue());
                } catch (IllegalArgumentException ignored) {};
                /*
                    Attention!

                    Since this constructor is created for convenience and the list of custom commands is optional,
                    errors in adding such commands are not handled.

                    Use the addNewCustomCommand() method if you need to catch such exceptions.
                 */
            }
        }
    }

    @Override
    // Run using this function even if multithreading is not used.
    public void run() {
        input.requestCommands();
    }

    public void addNewCustomCommand(String executionName, CommandMapEntry commandMapEntry) throws IllegalArgumentException {
        if (executionName == null || commandMapEntry == null) {
            throw new IllegalArgumentException("Custom command's name or/and map entry can't be null.");
        }

        if (commandManager.getCommand(executionName) != null) throw new IllegalArgumentException("There is already a command with this name for execution," +
                "you cannot replace already added or basic MVVM IO commands with new custom ones in order to avoid unexpected internal errors.");

        commandManager.setCommand(executionName, commandMapEntry);
    }
}
