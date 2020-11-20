package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;
import it.unibs.pajc.core.BaseModel;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ModelDama extends BaseModel {
    private HashMap<Integer,Pezzo> pezzi=new HashMap<>();

    public ModelDama(){

        //creo i pezzi a inizio partita

        for(int y=0;y<3;y++){
            for(int x=0;x<8;x++){
                if((x+y)%2!=0){
                    addPezzo(Pezzo.Fazione.Bianco,new Coordinates(x,y),false);
                }
            }
        }

        for(int y=5;y<8;y++){
            for(int x=0;x<8;x++){
                if((x+y)%2!=0){
                    addPezzo(Pezzo.Fazione.Nero,new Coordinates(x,y),false);
                }
            }
        }

    }

    public void addPezzo(Pezzo.Fazione fazione, Coordinates coordinates, boolean isDamone){
        Pezzo pezzo= isDamone?new Damone(fazione, coordinates):new Pedina(fazione, coordinates);
        pezzi.put(pezzo.getId(),pezzo);
        fireValuesChange(new ChangeEvent(this));

    }
/*
    public Pezzo isPresentPezzo(Coordinates coordinates){

      //  System.out.println(""+coordinates.x+":"+coordinates.y);
        //printPezzi();
        for (Pezzo p:pezzi) {
            if(p.x==coordinates.x){
               // System.out.println("Stessa x");

                if(p.y==coordinates.y)
                {
                  //  System.out.println("pezzo trovato");

                    return p;
                }
            }
        }
        System.out.println("pezzo non trovato");
        return null;
    }

    public void printPezzi(){
        for (Pezzo p:pezzi) {
            System.out.printf("\n%s\tx:%s y:%s \n",p.fazione,p.x,p.y);
        }
    }*/

    public Collection<Pezzo> getListaPezzi(){
        return pezzi.values();
    }

    public void removePezzo(Pezzo p)
    {
        //pezzi.remove(p);
        fireValuesChange(new ChangeEvent(this));

    }

    public boolean movePezzo(Pezzo p, Coordinates nuovaPosizione){
        //da fare dei ceck su nuova posizione
        p.x=nuovaPosizione.x;
        p.y=nuovaPosizione.y;
        fireValuesChange(new ChangeEvent(this));
        return true;
    }
}
