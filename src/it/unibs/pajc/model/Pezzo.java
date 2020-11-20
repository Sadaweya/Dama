package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;

import java.awt.*;


public abstract class Pezzo {


    public enum Fazione{
        Bianco,
        Nero
    }

    Fazione fazione;
    public int x;
    public int y;
    private static int incrementalId;
    private int id;


    public Pezzo(Fazione fazione, Coordinates coordinates) {
        this.fazione = fazione;
        this.x = coordinates.x;
        this.y = coordinates.y;
        this.id=incrementalId++;
    }

    public int getId() {
        return id;
    }


    public abstract boolean possoMuovere(Coordinates destinazione);

    public abstract Color getColor();

}


