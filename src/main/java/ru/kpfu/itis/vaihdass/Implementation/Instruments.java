package ru.kpfu.itis.vaihdass.Implementation;

public enum Instruments {
    PIANO(3, "Piano"), XYLOPHONE(14, "Xylophone"),
    VIOLIN(41, "Violin"), TRUMPET(57, "Trumpet"),
    TROMBONE(58, "Trombone"), CLARINET(72, "Clarinet"),
    FLUTE(74, "Flute"), TUBA(59, "Tuba");

    private final int cod;
    private final String name;

    Instruments(int cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    public int cod() {
        return cod;
    }

    public String getName() {
        return name;
    }
}
