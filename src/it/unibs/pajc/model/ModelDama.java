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
        //addPezzo(Pezzo.Fazione.Bianco,new Coordinates(0,1),false);
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
        ArrayList<Coordinates> listaPosizioniValide=new ArrayList<>();
        //System.out.printf("x: %d\ny: %d\n",c.x,c.y);
        Pezzo p=null;//c != da coordinate usate come chiave

        p=getPezzo(c);


        if(p!=null){
          // System.out.print("p!=null\n");
            listaPosizioniValide.add(new Coordinates(c.x,c.y)); //posizione di partenza viene illuminata

            int y;
            if(p.fazione== Pezzo.Fazione.Bianco)
                y=c.y+1;
            else
                y=c.y-1;
            //  QUI CONTROLLO SE LE CELLE SONO DENTRO LA SCACCHIERA E LIBERE E IN CASO LE ILLUMINO
            //  FUNZIONE RICORSIVA (LISTA CELLE CONTROLLA CELLA SUCCESSIVA)
            Coordinates temp=new Coordinates(c.x-1,y);
            if(isWithinBounds(temp) && !isPresentPezzoOnPosition(temp)){
                    listaPosizioniValide.add(temp);
            }else if(isWithinBounds(temp) && getPezzo(temp).fazione!=getPezzo(c).fazione){
                listaPosizioniValide.addAll(concatenaMosse(temp));
            }

            temp=new Coordinates(c.x+1,y);
            if(isWithinBounds(temp) && !isPresentPezzoOnPosition(temp)){
                listaPosizioniValide.add(temp);
            }else if(isWithinBounds(temp) && getPezzo(temp).fazione!=getPezzo(c).fazione){
                listaPosizioniValide.addAll(concatenaMosse(temp));
            }

            return listaPosizioniValide;
        }
        else
            return null;
    }
    private ArrayList<Coordinates> concatenaMosse(Coordinates posizione){
        ArrayList<Coordinates> mosse =new ArrayList<>();
        
        return mosse;

    }

    public boolean isWithinBounds(Coordinates c){
/*
        System.out.println("pezzo presente: "+!isPresentPezzoOnPosition(c));
        System.out.println("x<8: "+(c.x<8));
        System.out.println("x>0: "+(c.x>=0));
        System.out.println("y>0: "+(c.y>=0));
        System.out.println("y<8: "+(c.y<8));
 */
        return (c.x<8 && c.x>=0 && c.y>=0 && c.y<8);
    }

    public boolean isPresentPezzoOnPosition(Coordinates posizione){
        for (Pezzo pezzo:pezzi) {
            if(pezzo.equals(posizione)){
                return true;
                // System.out.println("trovato");
            }
            // System.out.println("non trovato");
        }
        return false;
    }

    private Pezzo getPezzo(Coordinates c){
        Pezzo p = null;
        for (Pezzo pezzo:pezzi) {
            if(pezzo.equals(c)){
                p = pezzo;
                break;
            }
        }
        return p;
    }

    public boolean movePezzo(Coordinates posizioneAttuale, Coordinates nuovaPosizione){
        boolean success=false;
        Pezzo p=getPezzo(posizioneAttuale);

        ArrayList<Coordinates> possibiliMosse= showMosse(p.posizione);
        for (Coordinates coordinates:possibiliMosse) {
            if (nuovaPosizione.equals(coordinates)) {
                p.posizione = nuovaPosizione;
                success = true;
                break;
            }
        }

        fireValuesChange(new ChangeEvent(this));
        return success;
    }
}
