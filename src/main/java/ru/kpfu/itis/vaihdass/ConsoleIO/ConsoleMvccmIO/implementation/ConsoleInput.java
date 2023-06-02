package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.implementation;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Controller;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Input;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleInput extends Input {
    private Scanner scanner;

    public ConsoleInput(Controller controller, InputStream inputStream) {
        super(controller);

        if (inputStream == null) throw new IllegalArgumentException("Input stream can't be null.");

        this.scanner = new Scanner(inputStream);
    }

    @Override
    public String requestNewCommand() {
        String command;
        try {
            if (scanner.hasNextLine()) {
                command = scanner.nextLine();
                if (command != null) {
                    setLastCommand(command);
                    return command;
                }
            }
        } catch (IllegalStateException e) {
            return null;
        }

        return null;
    }

    @Override
    public void requestCommands() {
        setLastCommand(""); // For the first default view
        lastCommandChanged();

        String command;
        while (scanner.hasNextLine()) {
            try {
                if ((command = scanner.nextLine()) != null) {
                    setLastCommand(command);
                    lastCommandChanged();
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                return;
            }
        }
    }
}
