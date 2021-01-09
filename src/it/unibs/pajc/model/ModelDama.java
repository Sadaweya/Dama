package it.unibs.pajc.model;

import com.sun.jdi.ArrayReference;
import it.unibs.pajc.Coordinates;
import it.unibs.pajc.core.BaseModel;

import javax.swing.event.ChangeEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

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

    private static ArrayList<Socket> clientList=new ArrayList<>();
    public ModelDama(Socket client){
       clientList.add(client);
    }

    public void addPezzo(Pezzo.Fazione fazione, Coordinates coordinates, boolean isDamone){
        Pezzo pezzo= isDamone?new Damone(fazione, coordinates):new Pedina(fazione, coordinates);
        pezzi.add(pezzo);
        fireValuesChange(new ChangeEvent(this));
    }

    public Collection<Pezzo> getListaPezzi(){
        return pezzi;
    }

    public void removePezzo(Pezzo p){
        System.out.println("pezzo mangiato= "+p.posizione);
        System.out.println(pezzi.remove(p));

        fireValuesChange(new ChangeEvent(this));

    }

    public ArrayList<Coordinates> showMosse(Coordinates c){
        return showMosse(getPezzo(c));
    }
    public ArrayList<Coordinates> showMosse(Pezzo pezzo){
        ArrayList<Coordinates> listaPosizioniValide=new ArrayList<>();

        //System.out.printf("x: %d\ny: %d\n",c.x,c.y);

        if(pezzo!=null){
          // System.out.print("p!=null\n");
            listaPosizioniValide.add(new Coordinates(pezzo.posizione.x,pezzo.posizione.y)); //posizione di partenza viene illuminata

            int y;
            if(pezzo.fazione== Pezzo.Fazione.Bianco)
                y=pezzo.posizione.y+1;
            else
                y=pezzo.posizione.y-1;

            Coordinates temp=new Coordinates(pezzo.posizione.x-1,y);
            if(isWithinBounds(temp) && !isPresentPezzoOnPosition(temp))
                listaPosizioniValide.add(temp);
            else if (isWithinBounds(temp) && isPresentPezzoOnPosition(temp) && getPezzo(temp).fazione!=pezzo.fazione)
                    listaPosizioniValide.addAll(ceck(new Coordinates(pezzo.posizione.x-1,y),pezzo,"sx"));


            temp=new Coordinates(pezzo.posizione.x+1,y);
            if(isWithinBounds(temp) && !isPresentPezzoOnPosition(temp))
                listaPosizioniValide.add(temp);
            else if (isWithinBounds(temp) && isPresentPezzoOnPosition(temp) && getPezzo(temp).fazione!=pezzo.fazione)
                    listaPosizioniValide.addAll(ceck(new Coordinates(pezzo.posizione.x+1,y),pezzo,"dx"));


            /*
            if(isWithinBounds(temp) && !isPresentPezzoOnPosition(temp)){
                listaPosizioniValide.add(temp);
            }else if(isWithinBounds(temp) && getPezzo(temp).fazione!=getPezzo(pezzo.posizione).fazione){

                int yt;
                if(pezzo.fazione== Pezzo.Fazione.Bianco)
                    yt=pezzo.posizione.y+2;
                else
                    yt=pezzo.posizione.y-2;

                Coordinates temp2=new Coordinates(pezzo.posizione.x-2,yt);
                if(isWithinBounds(temp2) && !isPresentPezzoOnPosition(temp2)){
                    listaPosizioniValide.add(temp);//pedina da mangiare
                    listaPosizioniValide.add(temp2);
                }
            }

             */


            /*
            if(isWithinBounds(temp) && !isPresentPezzoOnPosition(temp)){
                listaPosizioniValide.add(temp);
            }else if(isWithinBounds(temp) && getPezzo(temp).fazione!=getPezzo(pezzo.posizione).fazione){
                int yt;
                if(pezzo.fazione== Pezzo.Fazione.Bianco)
                    yt=pezzo.posizione.y+2;
                else
                    yt=pezzo.posizione.y-2;
                Coordinates temp2=new Coordinates(pezzo.posizione.x+2,yt);
                if(isWithinBounds(temp2) && !isPresentPezzoOnPosition(temp2)){
                    listaPosizioniValide.add(temp);//pedina da mangiare
                    listaPosizioniValide.add(temp2);
                }
            }

             */
            System.out.println(listaPosizioniValide);
            return listaPosizioniValide;
        }
        else
            return null;
    }


    private ArrayList<Coordinates> ceck(Coordinates cella, Pezzo pezzo,String direzione){
        ArrayList<Coordinates> listaPosizioniValide =new ArrayList<>();
        //System.out.println("sono qui per: "+cella);

            int y;
            if(pezzo.fazione== Pezzo.Fazione.Bianco)
                y=cella.y+1;
            else
                y=cella.y-1;
            Coordinates cellaSuccessiva;
            if(direzione.equals("sx"))
                cellaSuccessiva=new Coordinates(cella.x-1,y);
            else
                cellaSuccessiva=new Coordinates(cella.x+1,y);

            if(isWithinBounds(cellaSuccessiva) && !isPresentPezzoOnPosition(cellaSuccessiva)){
                //System.out.println("posso mangiare "+cella);
                listaPosizioniValide.add(cella);//pedina da mangiare
                listaPosizioniValide.add(cellaSuccessiva);//posizione futura

                if(pezzo.fazione== Pezzo.Fazione.Bianco)
                    y++;
                else
                    y--;

                Coordinates temp=new Coordinates(cellaSuccessiva.x-1,y);
                //System.out.println("questa è la cella successiva"+cellaSuccessiva);
                if (isWithinBounds(temp) && isPresentPezzoOnPosition(temp) && getPezzo(temp).fazione!=pezzo.fazione)
                    listaPosizioniValide.addAll(ceck(new Coordinates(cellaSuccessiva.x-1,y),pezzo,"sx"));

                temp= new Coordinates(cellaSuccessiva.x+1,y);
                if (isWithinBounds(temp) && isPresentPezzoOnPosition(temp) && getPezzo(temp).fazione!=pezzo.fazione)
                    listaPosizioniValide.addAll(ceck(new Coordinates(cellaSuccessiva.x+1,y),pezzo,"dx"));

            }
        return listaPosizioniValide;
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
            if (nuovaPosizione.equals(coordinates) && getPezzo(nuovaPosizione)==null) {
                if(Math.abs(nuovaPosizione.y-posizioneAttuale.y)==2)
                        removePezzo(getPezzo(new Coordinates((posizioneAttuale.x+nuovaPosizione.x)/2,(posizioneAttuale.y+ nuovaPosizione.y)/2)));
                p.posizione = nuovaPosizione;
                success = true;
                break;
            }
        }

        fireValuesChange(new ChangeEvent(this));
        return success;
    }
}
