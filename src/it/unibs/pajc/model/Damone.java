package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;

import java.awt.*;

public class Damone extends Pezzo {

    public Damone(Fazione fazione, Coordinates coordinates) {
        super(fazione, coordinates);
    }

    @Override
    public boolean equals(Coordinates coordinates) {
        return posizione.equals(coordinates);
    }

    @Override
    public boolean possoMuovere(Coordinates destinazione) {
        return false;
    }

    @Override
    public Fazione getFazione() {
        return fazione;
    }

}
