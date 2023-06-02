package ru.kpfu.itis.vaihdass.Implementation;

public enum NoteNotations {
    C(0), C1(1), D(2), D1(3), E(4), F(5), F1(6), G(7), G1(8), A(9), A1(10), B(11);
    private final int cod;

    NoteNotations(int cod) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }
}
