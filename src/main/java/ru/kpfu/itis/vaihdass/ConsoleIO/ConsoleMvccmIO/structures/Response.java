package ru.kpfu.itis.vaihdass.ConsoleIO.ConsoleMvccmIO.structures;

public class Response {
    private String output;
    private boolean linebreakAfter = true;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isLinebreakAfter() {
        return linebreakAfter;
    }

    public void setLinebreakAfter(boolean state) {
        this.linebreakAfter = state;
    }
}
