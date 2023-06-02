package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.implementation;

import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Model;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Output;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.exceptions.InputEmptyCommandException;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Request;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.ArgsParser;

public class SimpleController implements ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Controller {
    private final Output output;
    private final Model model;

    public SimpleController(Output output, Model model, String greeting) {
        if (output == null || model == null) throw new IllegalArgumentException("Output or/and model can't be null.");

        this.output = output;
        this.model = model;

        if (greeting != null) {
            Response response = new Response();
            response.setOutput(greeting);
            output.updateView(response);
        }
    }

    @Override
    public void update(Request request) {
        if (request == null || request.getInput() == null) return;

        // Properties getting or set default view (if empty input)
        Properties properties = new Properties(request.getInput(), output);
        try {
            ArgsParser.parseInput(request, properties);
        } catch (InputEmptyCommandException e) {
            Response response = new Response();
            setDefaultView(response);
            output.updateView(response);

            return;
        }

        // Update model and get output $message
        Response response = new Response();
        model.updateModel(properties, response);

        // Trying to set output $message or just do nothing (if empty message)
        try {
            output.updateView(response);
        } catch (IllegalArgumentException ignored) {}

        // Anyway set default view (after $message or even after nothing)
        setDefaultView(response);
        output.updateView(response);
    }

    // Using just for shorter syntax
    private void setDefaultView(Response response) {
        response.setOutput(model.getDefaultView());
        response.setLinebreakAfter(false);
    }
}
