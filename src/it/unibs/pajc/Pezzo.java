package it.unibs.pajc;

import java.awt.*;
import java.util.List;


public abstract class Pezzo {
    List<?> mosse;
    enum Fazione{
        Bianco,
        Nero
    }
    Fazione fazione;

    public Pezzo(Fazione fazione, int position) {
        this.fazione = fazione;
        this.position = position;
    }

    int position;

    public Color getColor(){
        return fazione==Fazione.Bianco?Color.GRAY:Color.BLUE;
    }
}


