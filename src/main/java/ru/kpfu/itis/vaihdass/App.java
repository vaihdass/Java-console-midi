package ru.kpfu.itis.vaihdass;

import ru.kpfu.itis.vaihdass.Abstractions.MusicPlayer;
import ru.kpfu.itis.vaihdass.Abstractions.MusicSaver;
import ru.kpfu.itis.vaihdass.Abstractions.MusicalScoreReader;
import ru.kpfu.itis.vaihdass.Abstractions.iApp;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.ConsoleIOFacade;
import ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.CommandMapEntriesBuilder;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomCommands.PlayDocumentCommand;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomCommands.PlayLiveCommand;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomCommands.SaveNotesCommand;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomProperties.PlayerProperties;
import ru.kpfu.itis.vaihdass.ConsoleIO.CustomProperties.SaverProperties;
import ru.kpfu.itis.vaihdass.Implementation.DefaultMusicPlayer;
import ru.kpfu.itis.vaihdass.Implementation.DefaultMusicSaver;
import ru.kpfu.itis.vaihdass.Implementation.DefaultMusicalScoreReader;

import static ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.utils.Color.yellow;

public class App implements iApp {
    private ConsoleIOFacade consoleIO;
    private MusicalScoreReader musicalScoreReader;
    private MusicPlayer musicPlayer;
    private MusicSaver musicSaver;

    public static void main(String[] args) {
        iApp app = new App();
    }

    public App() {
        init();
        start();
    }

    @Override
    public void init() {
        consoleIO = new ConsoleIOFacade(yellow("This is a program for playing MIDI-music.\n" +
                "Write 'help' to get information about all available commands."), () -> yellow(">>> "), System.in);

        musicalScoreReader = new DefaultMusicalScoreReader();
        musicPlayer = new DefaultMusicPlayer();
        musicSaver = new DefaultMusicSaver();

        consoleIO.addNewCustomCommand("playlive",
                CommandMapEntriesBuilder.getCommandEntry(
                      new PlayLiveCommand(),
                      new PlayerProperties(musicalScoreReader, musicPlayer),
                      "PlayLive",
                      "Plays the entered musical score.")
        );
        consoleIO.addNewCustomCommand("playdoc",
                CommandMapEntriesBuilder.getCommandEntry(
                        new PlayDocumentCommand(),
                        new PlayerProperties(musicalScoreReader, musicPlayer),
                        "Playdoc",
                        "Plays notes from the specified file.\n" +
                                "Usage: 'playdoc <Path>'.")
        );
        consoleIO.addNewCustomCommand("save",
                CommandMapEntriesBuilder.getCommandEntry(
                        new SaveNotesCommand(),
                        new SaverProperties(musicalScoreReader, musicSaver),
                        "Save",
                        "Save entered notes to the specified file.\n" +
                                "Usage: 'Save <Path>'.")
        );
    }

    @Override
    public void start() {
        consoleIO.run();
    }
}