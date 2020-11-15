package it.unibs.pajc;

import it.unibs.pajc.core.BaseModel;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class ModelDama extends BaseModel {
    ArrayList<Pezzo> pezzi=new ArrayList<>();

    ModelDama(){

        //conto come posizioni possibili solo le caselle nere
        for(int i=0;i<12;i++){
            addPedina(Pezzo.Fazione.Bianco,i);
        }
        for(int i=0;i<12;i++){
           addPedina(Pezzo.Fazione.Nero,20+i);
        }
    }

    public void addPedina(Pezzo.Fazione fazione, int position ){
        pezzi.add(new Pedina(fazione,position));
        fireValuesChange(new ChangeEvent(this));

    }

    public void addPedone(Pezzo.Fazione fazione, int position ){
        pezzi.add(new Damone(fazione,position));
        fireValuesChange(new ChangeEvent(this));

    }

    public void removePezzo(Pezzo p)
    {
        pezzi.remove(p);
        fireValuesChange(new ChangeEvent(this));

    }

    public boolean muoviPezzo(Pezzo p, int nuovaPosizione){
        p.position=nuovaPosizione;
        fireValuesChange(new ChangeEvent(this));
        return true;
    }
}
