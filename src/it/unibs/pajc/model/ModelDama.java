package it.unibs.pajc.model;

import it.unibs.pajc.Coordinates;
import it.unibs.pajc.core.BaseModel;

import javax.swing.event.ChangeEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ModelDama extends BaseModel {
    private ArrayList<Pezzo> pezzi=new ArrayList<>();

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
        pezzi.add(pezzo);
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
        return pezzi;
    }

    public void removePezzo(Pezzo p)
    {
        //pezzi.remove(p);
        fireValuesChange(new ChangeEvent(this));

    }

    public ArrayList<Coordinates> showMosse(Coordinates c){
        ArrayList<Coordinates> temp=new ArrayList<>();
        //System.out.printf("x: %d\ny: %d\n",c.x,c.y);
        Pezzo p=null;//c != da coordinate usate come chiave

        for (Pezzo pezzo:pezzi) {
            if(pezzo.posizioneEquals(c)){
                p = pezzo;
               // System.out.println("trovato");
                break;
            }
           // System.out.println("non trovato");
        }


        if(p!=null){
          // System.out.print("p!=null\n");
            int y;

            if(p.fazione== Pezzo.Fazione.Bianco)
                y=c.y+1;
            else
                y=c.y-1;

            temp.add(new Coordinates(c.x-1,y));
            temp.add(new Coordinates(c.x+1,y));
            //qui vanno aggiunti i controlli per verificare che le posse siano davvero disponibili
            return temp;
        }
        else
            return null;


    }

    public boolean movePezzo(Pezzo p, Coordinates nuovaPosizione){
        //da fare dei ceck su nuova posizione
        p.posizione=nuovaPosizione;
        fireValuesChange(new ChangeEvent(this));
        return true;
    }
}
