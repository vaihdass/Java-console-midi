package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.CommandManager;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.commands.ExitCommand;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.commands.HelpCommand;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.CommandInformation;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.CommandMapEntry;

import java.util.*;

public abstract class CommandMapEntriesBuilder {
    public static CommandMapEntry getCommandEntry(Command command, ExpandableProperties expandableProperties,
                                                  String descriptionName, String description) {
        if (command == null) return null;

        if (descriptionName == null) return new CommandMapEntry(command, null, expandableProperties);

        return new CommandMapEntry(command, new CommandInformation(descriptionName, description == null ? "No description." : description),
                expandableProperties);
    }

    public static List<CommandMapEntry> getMultiAliasesCommandEntries(List<String> executionNames, Command command,
                                                                      ExpandableProperties expandableProperties,
                                                                      String description) {
        if (executionNames == null) return null;

        Objects.requireNonNull(executionNames);
        if (executionNames.size() == 0) return null;

        List<CommandMapEntry> resultEntriesList = new LinkedList<>();
        resultEntriesList.add(getCommandEntry(command, expandableProperties,
                collapseExecutionNamesToDescription(executionNames), description));

        if (executionNames.size() >= 2) {
            executionNames.subList(1, executionNames.size()).stream().map(o -> getCommandEntry(command, expandableProperties,
                    null, null)).forEach(resultEntriesList::add);
        }

        return resultEntriesList;
    }

    private static String collapseExecutionNamesToDescription(List<String> executionNames) {
        return String.join(", ", Objects.requireNonNull(executionNames));
    }

    public static void fillCommandManagerWithBasicCommands(CommandManager commandManager) {
        fillCommandManagerWithBasicCommand(commandManager, Arrays.asList("help", "man", "помощь", "команды"), new HelpCommand(),
                "Displays a list of all available commands with a description.");

        fillCommandManagerWithBasicCommand(commandManager, Arrays.asList("exit", "close", "выход"), new ExitCommand(),
                "Allows to exit the program by terminating its process.");
    }

    private static void fillCommandManagerWithBasicCommand(CommandManager commandManager,
                                                           List<String> commandAliases, Command command, String description) {
        List<CommandMapEntry> commands = getMultiAliasesCommandEntries(
                commandAliases,
                command,
                null,
                description
        );
        for (int i = 0; i < commands.size(); i++) {
            commandManager.setCommand(commandAliases.get(i), commands.get(i));
        }
    }
}
