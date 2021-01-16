package it.unibs.pajc;

import it.unibs.pajc.model.ModelDama;

import java.net.Socket;

public class Partita implements Runnable{

    Socket player1;
    Socket player2;
    ModelDama game;

    public Partita(Socket player1, Socket player2, ModelDama model) {
        this.player1=player1;
        this.player2=player2;
        this.game=model;
    }

    @Override
    public void run() {


    }
}
