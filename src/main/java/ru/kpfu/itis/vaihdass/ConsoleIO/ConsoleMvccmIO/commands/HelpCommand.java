package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.commands;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.CommandMapEntry;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;

import java.util.Map;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.red;

public class HelpCommand extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        if (properties == null || properties.getCommandManager() == null) {
            setInternalErrorView(response);
            return;
        }

        StringBuilder resultString = new StringBuilder(red(">>>>>> HELP <<<<<<") + "\n");
        for (final Map.Entry<String, CommandMapEntry> commandEntry : properties.getCommandManager().getCommands().entrySet()) {
            if (commandEntry.getValue().getCommandInformation() != null) {
                resultString.append(commandEntry.getValue().getCommandInformation().toString()).append("\n");
            }
        }

        response.setLinebreakAfter(false);
        response.setOutput(resultString.toString());
    }
}
