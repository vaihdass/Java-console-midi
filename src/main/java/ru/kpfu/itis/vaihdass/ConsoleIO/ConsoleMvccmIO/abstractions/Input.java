package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Request;

public abstract class Input {
    private Controller controller;
    private String lastCommand;

    public Input(Controller controller) throws IllegalArgumentException {
        setController(controller);
    }

    public abstract String requestNewCommand();
    public abstract void requestCommands();

    public void lastCommandChanged() {
        controller.update(new Request(lastCommand, this));
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) throws IllegalArgumentException {
        if (lastCommand == null) throw new IllegalArgumentException("Last command can't be null.");
        this.lastCommand = lastCommand;
    }

    public void setController(Controller controller) {
        if (controller == null) throw new IllegalArgumentException("Controller can't be null");
        this.controller = controller;
    }
}
