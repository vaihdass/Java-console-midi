package ru.kpfu.itis.vaihdass.ConsoleIO.CustomCommands;

import ru.kpfu.itis.vaihdass.Abstractions.MusicalScore;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.Command;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.abstractions.ExpandableProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Properties;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures.Response;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomProperties.PlayerProperties;
import ru.kpfu.itis.vaihdass.Implementation.Instruments;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.*;

public class PlayLiveCommand extends Command {
    @Override
    public void execute(Response response, Properties properties, ExpandableProperties expandableProperties) {
        if (!(expandableProperties instanceof PlayerProperties) || properties.getInput() == null || properties.getOutput() == null) {
            setWrongExpandablePropsView(response);
            return;
        }
        PlayerProperties additionalData = (PlayerProperties) expandableProperties;

        if (additionalData.getMusicalScoreReader() == null || additionalData.getMusicPlayer() == null) {
            setWrongExpandablePropsView(response);
            return;
        }

        Response infoResponse = new Response();
        infoResponse.setOutput(green("This is a MuseEditor. To get your notes, write down the necessary information line by line like this:\n" +
                "Tempo (< 750), Instrument (choose from the list below)\n" +
                "Note (below how to set a note), Duration (below how to interpret it), Volume (pp, p, mp, mf, f, ff)\n" +
                "... and as many notes as you want, until you write 'end'.\n"));
        infoResponse.setOutput(infoResponse.getOutput() +
                yellow("~~~ Instruments: ~~~\n" + getInstrumentsInfo()));
        infoResponse.setOutput(infoResponse.getOutput() +
                cyan("Notes: {Octave}A-G{# or nothing}\n" +
                        "{Octave}: Control octave, major and minor octaves -> -2, -1, 0. 1-5 octaves -> 1-5\n" +
                        "Duration: 1 of sound duration = a thirty-second note at a preset tempo.\n" +
                        "(ex. the fourth note will be 8, and the eighth note will be 4."));
        properties.getOutput().updateView(infoResponse);

        try {
            StringBuilder musicalScoreStringify = new StringBuilder();
            String line;
            while (!(line = properties.getInput().requestNewCommand()).equalsIgnoreCase("end")) {
                musicalScoreStringify.append(line).append("\n");
            }

            MusicalScore musicalScore = additionalData.getMusicalScoreReader().readMusicalScore(musicalScoreStringify.toString());
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

    private String getInstrumentsInfo() {
        StringBuilder result = new StringBuilder();
        for (final Instruments instrument : Instruments.values()) {
            result.append(instrument.getName()).append("\n");
        }
        return result.toString();
    }
}
