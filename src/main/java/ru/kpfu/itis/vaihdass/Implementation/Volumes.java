package ru.kpfu.itis.vaihdass.Implementation;

public enum Volumes {
    PP(20, "pp"), P(35, "p"), MP(50, "mp"),
    MF(60, "mf"), F(75, "f"), FF(90, "ff");

    private final int cod;
    private final String name;

    Volumes(int cod, String name) {
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
