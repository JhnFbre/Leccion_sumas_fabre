package com.example.walther.leccion;

public class Puntaje {
    private int bueno, malo;

    public int getBueno() {
        return bueno;
    }

    public void setBueno(int bueno) {
        this.bueno = bueno;
    }

    public int getMalo() {
        return malo;
    }

    public void setMalo(int malo) {
        this.malo = malo;
    }

    public Puntaje(int bueno, int malo) {
        this.bueno = bueno;
        this.malo = malo;
    }
}
