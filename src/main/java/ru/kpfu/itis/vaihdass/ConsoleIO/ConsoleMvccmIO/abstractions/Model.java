package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;

public interface Model {
    void updateModel(Properties properties, Response response);
    String getDefaultView();
}
