package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;

import java.awt.*;

public class Pedina extends Pezzo{

    public Pedina(Fazione fazione, Coordinates coordinates) {
        super(fazione, coordinates);
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

