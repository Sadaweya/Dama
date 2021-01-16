package it.unibs.pajc;

import it.unibs.pajc.core.BaseModel;
import it.unibs.pajc.model.ModelDama;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static BaseModel model;

    public static void main(String[] args){
        int port=1234;

        try (
                ServerSocket server = new ServerSocket(port);
        ) {
            ArrayList<Partita> openGamesList =new ArrayList<>();

            while(true) {
                Socket player1 = server.accept();
                Socket player2 = server.accept();
                ModelDama model=new ModelDama();
                Partita partita = new Partita(player1,player2,model);
                Thread game  = new Thread(partita);
                game.start();
            }

        } catch(IOException e) {
            System.err.printf("\nErrore di comunicazione: %s\n", e);
        }
    }

    public BaseModel getModel(){
        return model;
    }
}
