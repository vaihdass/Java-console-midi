package ru.kpfu.itis.vaihdass.ConsoleIO.CustomCommands;

import ru.kpfu.itis.vaihdass.Abstractions.MusicalScore;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomProperties.PlayerProperties;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.green;
import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.red;

public class PlayDocumentCommand extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        if (!(expandableProperties instanceof PlayerProperties) || properties.getOutput() == null) {
            setWrongExpandablePropsView(response);
            return;
        }
        PlayerProperties additionalData = (PlayerProperties) expandableProperties;

        if (additionalData.getMusicalScoreReader() == null || additionalData.getMusicPlayer() == null) {
            setWrongExpandablePropsView(response);
            return;
        }

        if (properties.getArgs() == null || properties.getArgs().size() < 1 || properties.getArgs().get(0) == null) {
            response.setOutput(red("The expected <Path> argument is not entered.\n" +
                    "Write 'help' to get an example of the correct use of this command."));
            return;
        }
        try {
            Path filepath;
            try {
                filepath = Paths.get(properties.getArgs().get(0).trim()).normalize().toAbsolutePath();
            } catch (InvalidPathException e) {
                response.setOutput(red("The expected <Path> argument is entered incorrectly."));
                return;
            }

            if (filepath.toFile().isDirectory() || Files.notExists(filepath)) {
                response.setOutput(red("Expected argument <Path>: No such file exists."));
                return;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(filepath));
            StringBuilder resultString = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultString.append(line).append('\n');
            }

            MusicalScore musicalScore = additionalData.getMusicalScoreReader().readMusicalScore(resultString.toString());
            if (musicalScore == null) {
                response.setOutput(red("The musical score cannot be recognized. Notes in an incorrect format."));
                return;
            }

            additionalData.getMusicPlayer().play(musicalScore);
            response.setOutput(green("~~~ The music playback is finished. ~~~"));
        } catch (Exception e) {
            setInternalErrorView(response);
        }
    }
}
