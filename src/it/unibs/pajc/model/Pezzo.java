package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;

import java.awt.*;
import java.util.ArrayList;


public abstract class Pezzo {


    public enum Fazione{
        Bianco,
        Nero
    }

    Fazione fazione;
    public Coordinates posizione;
    public ArrayList<Pezzo> pezziMangiati;



    public Pezzo(Fazione fazione, Coordinates coordinates) {
        this.fazione = fazione;
        this.posizione = coordinates;
    }



    public abstract boolean equals(Coordinates coordinates);

    public abstract boolean possoMuovere(Coordinates destinazione);

    public abstract Fazione getFazione();

}


