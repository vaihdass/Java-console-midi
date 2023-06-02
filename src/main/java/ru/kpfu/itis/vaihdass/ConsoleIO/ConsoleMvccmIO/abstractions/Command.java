package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.red;

public abstract class Command {
    // Use it when the wrong instance of additional properties is passed to the custom command
    public void setWrongExpandablePropsView(Response response) {
        response.setOutput(red("Error: There's no access to all the resources necessary to execute this command."));
        response.setLinebreakAfter(true);
    }
    public void setInternalErrorView(Response response) {
        response.setOutput(red("Error: There's no access to all the expected resources."));
        response.setLinebreakAfter(true);
    }
    public abstract void execute(Response response, Properties properties, ExpandableProperties expandableProperties);
}
