package it.unibs.pajc;

import java.awt.*;

public class Pedina {

    boolean isDamone;
    boolean isBianco;
    int position;
    int id;

    public Pedina(boolean isDamone,boolean isBianco, int position,int id) {
        this.isDamone = isDamone;
        this.isBianco = isBianco;
        this.position = position;
        this.id=id;
    }

    public Color getColor(){
        return isBianco?Color.GRAY:Color.BLUE;

    }
}

