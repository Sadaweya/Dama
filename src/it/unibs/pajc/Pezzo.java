package it.unibs.pajc;

import java.awt.*;


public abstract class Pezzo {

    enum Fazione{
        Bianco,
        Nero
    }

    enum Kind{
        Pedina,
        Damone;
    }

    Fazione fazione;
    Kind kind;

    int x;
    int y;

    public Pezzo(Fazione fazione, Coordinates coordinates, Kind kind) {
        this.fazione = fazione;
        this.x = coordinates.x;
        this.y = coordinates.y;
        this.kind = kind;
    }


    public Color getColor(){
        return fazione==Fazione.Bianco?Color.GRAY:Color.BLUE;
    }
}


