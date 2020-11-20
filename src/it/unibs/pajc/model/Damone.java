package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;

import java.awt.*;

public class Damone extends Pezzo {

    public Damone(Fazione fazione, Coordinates coordinates) {
        super(fazione, coordinates);
    }

    @Override
    public boolean possoMuovere(Coordinates destinazione) {
        return false;
    }

    @Override
    public Color getColor() {
        return fazione==Fazione.Bianco?Color.GRAY:Color.BLUE;
    }

}
