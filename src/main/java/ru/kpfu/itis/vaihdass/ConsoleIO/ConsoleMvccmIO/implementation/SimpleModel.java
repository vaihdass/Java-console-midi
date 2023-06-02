package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.implementation;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.CommandManager;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.DefaultViewGetter;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Model;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;

public class SimpleModel implements Model {
    private final CommandManager commandManager;
    private final DefaultViewGetter defaultViewGetterCallback;

    public SimpleModel(CommandManager commandManager, DefaultViewGetter defaultViewGetter) {
        if (commandManager == null || defaultViewGetter == null) {
            throw new IllegalArgumentException("Command manager or/and get default view callback can't be null.");
        }

        this.commandManager = commandManager;
        this.defaultViewGetterCallback = defaultViewGetter;
    }

    @Override
    public void updateModel(Properties properties, Response response) {
        ExpandableProperties expandableProperties =
                properties.getCommand() != null && commandManager.getCommand(properties.getCommand()) != null
                        && commandManager.getCommandMapEntry(properties.getCommand()) != null
                ? commandManager.getCommandMapEntry(properties.getCommand()).getExpandableProperties()
                : null;

        commandManager.executeCommand(response, properties, expandableProperties);
    }

    @Override
    public String getDefaultView() {
        return defaultViewGetterCallback.getDefaultView();
    }
}
