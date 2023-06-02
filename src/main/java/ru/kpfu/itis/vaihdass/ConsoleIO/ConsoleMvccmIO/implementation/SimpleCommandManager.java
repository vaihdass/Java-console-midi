package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.implementation;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.CommandManager;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.CommandMapEntry;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;

import java.util.HashMap;
import java.util.Map;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.red;

public class SimpleCommandManager implements CommandManager {
    private final Map<String, CommandMapEntry> commandsMap;

    public SimpleCommandManager() {
        commandsMap = new HashMap<>();
    }

    @Override
    public void executeCommand(Response response, Properties properties, ExpandableProperties expandableProperties) {
        if (properties == null || properties.getCommand() == null) return;

        Command commandLower = getCommand(properties.getCommand().toLowerCase());
        if (commandLower == null) {
            response.setOutput(red("Error: Such command wasn't found."));
            return;
        }

        properties.setCommandManager(this);

        commandLower.execute(response, properties, expandableProperties);
    }

    @Override
    public void setCommand(String executionName, CommandMapEntry commandMapEntry) {
        if (executionName == null || commandMapEntry == null) {
            throw new IllegalArgumentException("Command's execution name or/and map entry can't be null.");
        }

        commandsMap.put(executionName, commandMapEntry);
    }

    @Override
    public void removeCommand(String commandName) {
        if (commandName == null) return;

        commandsMap.remove(commandName);
    }

    @Override
    public Command getCommand(String commandName) {
        if (commandName == null || commandsMap.get(commandName) == null) return null;

        return commandsMap.get(commandName).getCommand();
    }

    @Override
    public CommandMapEntry getCommandMapEntry(String commandName) {
        if (commandName == null) return null;

        return commandsMap.get(commandName);
    }

    @Override
    public Map<String, CommandMapEntry> getCommands() {
        return commandsMap;
    }
}
